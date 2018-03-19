package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.ArtistDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;
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
 * #ArtistDao interface implementation for the MySQL db {@link Artist}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */


@Repository
public class SqlArtistDao extends AbstractModelDao<Artist> implements ArtistDao {

    @Autowired
    private SqlGenreDao genreDao;

    private static final Logger LOGGER = LogManager.getLogger(SqlArtistDao.class);

    private static final String CREATE_QUERY =
        "INSERT INTO t_artist (genre_id, artist_description, artist_image, artist_title) VALUES (?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_artist";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_artist where artist_id = ?;";
    private static final String SELECT_QUERY_BY_GENRE = "select * from t_artist where genre_id = ?";
    private static final String UPDATE_QUERY =
        "update t_artist set artist_description = ?, artist_image = ? where artist_title = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_artist WHERE artist_title = ?;";
    private static final String BY_NAME_QUERY = "select * from t_artist where artist_title = ?;";

    private static final String ARTIST_ID = "artist_id";
    private static final String GENRE_ID = "genre_id";
    private static final String ARTIST_TITLE = "artist_title";
    private static final String ARTIST_DESCRIPTION = "artist_description";
    private static final String ARTIST_IMAGE = "artist_image";

    @Override
    protected String getCreateQuery() {
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


    public String getByNameQuery() {
        return BY_NAME_QUERY;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Artist model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Artist artist =  model;
        ps.setLong(1, artist.getGenre().getId());
        ps.setString(2, artist.getDescription());
        ps.setString(3, artist.getImage());
        ps.setString(4, artist.getTitle());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Artist artist = (Artist) model;
        ps.setString(1, artist.getDescription());
        ps.setString(2, artist.getImage());
        ps.setString(3, artist.getTitle());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Artist model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(getDeleteQuery());
        Artist artist = (Artist) model;

        ps.setString(1, artist.getTitle());

        return ps.executeUpdate();

    }

    @Override
    protected Artist parseResult(ResultSet rs) throws DAOException {
        Artist artist = new Artist();



        try {
            artist.setId(rs.getLong(ARTIST_ID));
            Genre genre = genreDao.getById(rs.getLong(GENRE_ID));
            artist.setGenre(genre);
            artist.setTitle(rs.getString(ARTIST_TITLE));
            artist.setDescription(rs.getString(ARTIST_DESCRIPTION));
            artist.setImage(rs.getString(ARTIST_IMAGE));
        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }

        return artist;
    }

    public List<Artist> getByGenre(Long genreId) throws DAOException {

        try ( Connection con = ConnectionPool.takeConnection();
              PreparedStatement ps = con.prepareStatement(SELECT_QUERY_BY_GENRE)) {

            ps.setLong(1, genreId);

            ResultSet rs = ps.executeQuery();

            List<Artist> list = new ArrayList<>();

            while (rs.next()) {
                Artist artist = parseResult(rs);
                list.add(artist);
            }
            return list;

        } catch (ConnectionPoolException e) {
            LOGGER.error( "Connection is not open", e);
            throw new DAOException( "Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        }
    }

    @Override
    public Artist getByName(String title) throws DAOException {

        try( Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_NAME_QUERY)) {

            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            Artist artist = null;

            if (rs.next()) {
                artist = parseResult(rs);
            }

            return artist;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error( "Connection is not open");
            throw new DAOException( "Connection is not open");
        }

    }
}
