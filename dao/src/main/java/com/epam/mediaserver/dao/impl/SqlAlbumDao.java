package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.AlbumDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * #AlbumDao interface implementation for the MySQL db {@link Album}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SqlAlbumDao extends AbstractModelDao<Album> implements AlbumDao {

    @Autowired
    private SqlArtistDao artistDao;

    private static final Logger LOGGER = LogManager.getLogger(SqlAlbumDao.class);

    private static final String CREATE_QUERY =
        "INSERT INTO t_album (artist_id, album_year, album_description, album_image ,album_title) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_album;";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_album where album_id = ?;";
    private static final String UPDATE_QUERY =
        "update t_album set artist_id = ?, album_year = ? ,album_description = ? , album_image = ?" +
        "where album_title = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_album WHERE album_title = ?;";

    private static final String BY_ARTIST_QUERY = "SELECT * FROM t_album " +
                                                  "WHERE artist_id = ?";
    private static final String BY_NAME_QUERY = "SELECT * FROM t_album WHERE album_title = ?;";


    private static final String ALBUM_ID = "album_id";
    private static final String ARTIST_ID = "artist_id";
    private static final String ALBUM_YEAR = "album_year";
    private static final String ALBUM_TITLE = "album_title";
    private static final String ALBUM_DESCRIPTION = "album_description";
    private static final String ALBUM_IMAGE = "album_image";

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getSelectQueryWithID() {
        return SELECT_QUERY_WITH_ID;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Album model, String query) throws SQLException {

        int counter;

        try (PreparedStatement ps = con.prepareStatement(query)) {
            Album album = (Album) model;
            ps.setLong(1, album.getArtist().getId());
            ps.setInt(2, album.getYear());
            ps.setString(3, album.getDescription());
            ps.setString(4, album.getImage());
            ps.setString(5, album.getTitle());

            counter = ps.executeUpdate();
        }
        return counter;
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        return this.preparedStatementForCreate(con, (Album) model, query);
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Album model, String query) throws SQLException {

        int counter;

        try (PreparedStatement ps = con.prepareStatement(getDeleteQuery())) {
            Album album = (Album) model;
            ps.setString(1, album.getTitle());

            counter = ps.executeUpdate();
        }

        return counter;
    }

    @Override
    protected Album parseResult(ResultSet rs) throws DAOException {
        Album album = new Album();

        try {
            album.setId(rs.getLong(ALBUM_ID));

            Artist artist = artistDao.getById(rs.getLong(ARTIST_ID));
            album.setArtist(artist);
            album.setYear(rs.getInt(ALBUM_YEAR));
            album.setTitle(rs.getString(ALBUM_TITLE));
            album.setDescription(rs.getString(ALBUM_DESCRIPTION));
            album.setImage(rs.getString(ALBUM_IMAGE));
        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }

        return album;
    }

    @Override
    public List<Album> getByArtist(Long artistId) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_ARTIST_QUERY)) {

            List<Album> list = new ArrayList<>();

            ps.setLong(1, artistId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model album = parseResult(rs);
                list.add((Album) album);
            }

            return list;

        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open");
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }
    }

    @Override
    public Album getByName(String title) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_NAME_QUERY)) {

            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            Album album = null;

            if (rs.next()) {
                album = (Album) parseResult(rs);
            }

            return album;
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open");
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }


    }
}
