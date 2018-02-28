package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.ArtistDao;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class SqlArtistDao extends AbstractModelDao implements ArtistDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlArtistDao.class);

    private static final String CREATE_QUERY =
        "INSERT INTO t_artist (genre_id, artist_description, artist_image, artist_title) VALUES (?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_artist";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_artist where artist_id = ?;";
    private static final String SELECT_QUERY_BY_GENRE = "select * from t_artist where genre_id = " +
                                                        "(select genre_id from t_genre where genre_title = ?);";
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
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Artist artist = (Artist) model;
        ps.setInt(1, artist.getGenre().getId());
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
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(getDeleteQuery());
        Artist artist = (Artist) model;

        ps.setString(1, artist.getTitle());

        return ps.executeUpdate();

    }

    @Override
    protected Model parseResult(ResultSet rs) throws DAOException {
        Artist artist = new Artist();

        SqlFactory factory = SqlFactory.getInstance();

        try {
            artist.setId(rs.getInt(ARTIST_ID));
            Genre genre = (Genre) factory.getGenreDao().getById(rs.getInt(GENRE_ID));
            artist.setGenre(genre);
            artist.setTitle(rs.getString(ARTIST_TITLE));
            artist.setDescription(rs.getString(ARTIST_DESCRIPTION));
            artist.setImage(rs.getString(ARTIST_IMAGE));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new DAOException(SQL_EXCEPTION);
        }

        return artist;
    }

    public List<Artist> getByGenre(String genre) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Artist> list = new ArrayList<>();

        try {
            con = ConnectionPool.takeConnection();

            ps = con.prepareStatement(SELECT_QUERY_BY_GENRE);

            ps.setString(1, genre);

            rs = ps.executeQuery();

            while (rs.next()) {
                Artist artist = (Artist) parseResult(rs);
                list.add(artist);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXCEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXCEPTION);
            }
        }

        return list;
    }

    @Override
    public Artist getByName(String title) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Artist artist = null;

        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(BY_NAME_QUERY);
            ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
                artist = (Artist) parseResult(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new DAOException(SQL_EXCEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXCEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXCEPTION);
            }
        }

        return artist;
    }
}
