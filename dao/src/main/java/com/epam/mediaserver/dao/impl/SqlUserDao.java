package com.epam.mediaserver.dao.impl;


import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.UserDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.exeption.PersistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDao extends AbstractModelDao implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(SqlUserDao.class);

    private static final String CREATE_QUERY = "INSERT INTO t_user " +
                                               "(user_login, user_password, user_name, user_surname,  user_adminRoot,user_email, user_photo) \n"
                                               +
                                               "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_QUERY = "Select * FROM t_user";
    private static final String SELECT_QUERY_BY_LIST = "SELECT * FROM t_user LIMIT ? OFFSET ?";
    private static final String SELECT_QUERY_WITH_ID = "Select * FROM t_user WHERE user_id= ?";
    private static final String UPDATE_QUERY =
        "UPDATE t_user SET user_login = ?, user_name = ?, user_surname = ?, user_email= ?, " +
        " user_adminRoot = ?, user_photo = ? WHERE user_id = ? ;";
    private static final String DELETE_QUERY = "DELETE FROM t_user WHERE user_email=?;";
    private static final String BY_AUTHORIZATION_QUERY = "SELECT * FROM t_user WHERE user_login=?;";
    private static final String SET_PHOTO_QUERY = "UPDATE t_user SET user_photo = ? WHERE user_login= ? ;";
    private static final String CHECK_LOGIN_QUERY = "SELECT user_login FROM t_user WHERE user_login = ?;";
    private static final String CHECK_EMAIL_QUERY = "SELECT user_email FROM t_user WHERE user_email = ? ;";
    private static final String PAGES_RETURN_QUERY = "SELECT count(user_id) AS count FROM t_user;";


    private static final Integer LIMIT_LIST = 3;
    private static final String USER_ID = "user_id";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_NAME = "user_name";
    private static final String USER_SURNAME = "user_surname";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PHOTO = "user_photo";
    private static final String USER_ADMIN_ROOT = "user_adminRoot";
    private static final String USER_FIELDS_COUNT = "count";

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

    @Override
    public void setPhoto(String photo, String login) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(SET_PHOTO_QUERY);

            ps.setString(1, photo);
            ps.setString(2, login);

            int counter = ps.executeUpdate();

            if (counter != 1) {
                throw new PersistException(PERSIST_EXCEPTION);
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        } catch (PersistException e) {
            LOGGER.error(PERSIST_EXCEPTION, e);
            throw new DAOException(PERSIST_EXCEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXCEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXCEPTION);
            }
        }
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);

        User user = (User) model;
        ps.setString(1, user.getLogin());
        ps.setLong(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getSurname());
        ps.setBoolean(5, user.isAdminRoot());
        ps.setString(6, user.getEmail());
        ps.setString(7, user.getPhoto());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        User user = (User) model;
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getName());
        ps.setString(3, user.getSurname());
        ps.setString(4, user.getEmail());
        ps.setBoolean(5, user.isAdminRoot());
        ps.setString(6, user.getPhoto());
        ps.setInt(7, user.getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(getDeleteQuery());
        User user = (User) model;

        ps.setString(1, user.getEmail());

        return ps.executeUpdate();
    }

    @Override
    protected Model parseResult(ResultSet rs) throws SQLException {

        User user = new User();
        user.setId(rs.getInt(USER_ID));
        user.setLogin(rs.getString(USER_LOGIN));
        user.setPassword(rs.getLong(USER_PASSWORD));
        user.setName(rs.getString(USER_NAME));
        user.setSurname(rs.getString(USER_SURNAME));
        user.setEmail(rs.getString(USER_EMAIL));
        user.setPhoto(rs.getString(USER_PHOTO));
        user.setAdminRoot(rs.getBoolean(USER_ADMIN_ROOT));
        return user;
    }

    @Override
    public User authorisation(String login) throws DAOException {

        Model user;

        try(Connection con = ConnectionPool.takeConnection();
            PreparedStatement  ps = con.prepareStatement(BY_AUTHORIZATION_QUERY)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            rs.next();

            user = parseResult(rs);

        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        }

        return (User) user;
    }


    @Override
    public User registration(String login, long password, String email, String name, String surname)
        throws DAOException {

        User user = new User(login, password, email, name, surname, false);

        add(user);

        return user;
    }


    @Override
    public boolean checkLogin(String login) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(CHECK_LOGIN_QUERY);
            ps.setString(1, login);

            rs = ps.executeQuery();
            rs.next();

            if (rs.getString(USER_LOGIN) == null) {
                return true;
            } else {
                return true;
            }

        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXCEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXCEPTION);
            }
        }
    }

    @Override
    public boolean checkEmail(String email) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(CHECK_EMAIL_QUERY);
            ps.setString(1, email);

            rs = ps.executeQuery();
            rs.next();

            try {
                rs.getString(USER_EMAIL);
                return true;
            } catch (SQLException e) {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXCEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXCEPTION);
            }
        }
    }

    public List<User> getUserList(int page) throws DAOException {

        List<User> list = new ArrayList<>();

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY_BY_LIST)) {

            ps.setInt(1, LIMIT_LIST);
            ps.setInt(2, (page * LIMIT_LIST) - LIMIT_LIST);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = (User) parseResult(rs);
                list.add(user);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION, e);
            throw new DAOException(SQL_EXCEPTION);
        }

        return list;
    }

    public Integer getPage() throws DAOException {

        String count;

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(PAGES_RETURN_QUERY);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            count = rs.getString(USER_FIELDS_COUNT);
            ConnectionPool.closeConnection(con,ps,rs);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXCEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXCEPTION);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(SQL_EXCEPTION);
        }
        return Integer.valueOf(count) / LIMIT_LIST;
    }
}

