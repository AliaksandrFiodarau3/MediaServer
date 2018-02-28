package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.GenreDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
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

/**
 * #GenreDao interface implementation for the MySQL db {@link GenreDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

public class SqlGenreDao extends AbstractModelDao implements GenreDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlGenreDao.class);

    private static final String
        CREATE_QUERY =
        "insert into t_genre (genre_description, genre_title, genre_image ) values (?,?,?);";
    private static final String SELECT_QUERY = "select * from t_genre;";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_genre where genre_id = ?;";
    private static final String SELECT_QUERY_WITH_NAME = "select * from t_genre where genre_title = ?;";
    private static final String UPDATE_QUERY = "update t_genre set genre_description = ? where genre_title = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_genre WHERE genre_title = ?;";

    private static final String GENRE_ID = "genre_id";
    private static final String GENRE_TITLE = "genre_title";
    private static final String GENRE_DESCRIPTION = "genre_description";
    private static final String GENRE_IMAGE = "genre_image";

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
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);

        Genre genre = (Genre) model;
        ps.setString(1, genre.getDescription());
        ps.setString(2, genre.getTitle());
        ps.setString(3, genre.getImage());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        return preparedStatementForCreate(con, model, query);
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(getDeleteQuery());
        Genre genre = (Genre) model;

        ps.setString(1, genre.getTitle());

        return ps.executeUpdate();
    }

    @Override
    protected Model parseResult(ResultSet rs) throws SQLException {

        Genre genre = new Genre();
        genre.setId(rs.getInt(GENRE_ID));
        genre.setTitle(rs.getString(GENRE_TITLE));
        genre.setDescription(rs.getString(GENRE_DESCRIPTION));
        genre.setImage(rs.getString(GENRE_IMAGE));

        return genre;
    }

    @Override
    public Genre getByName(String title) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Genre genre = null;
        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(SELECT_QUERY_WITH_NAME);
            ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
                genre = (Genre) parseResult(rs);
            }

        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
            }
        }

        return genre;

    }
}
