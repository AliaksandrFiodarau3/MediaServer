package com.epam.mediaserver.dao.impl;


import com.epam.mediaserver.builder.BuilderFactory;
import com.epam.mediaserver.constant.Number;
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

public class SqlUserDao extends AbstractModelDao<User> implements UserDao {

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
    private static final String PAGES_RETURN_SEARCHE_QUERY = "Select count(user_id) AS count FROM t_user WHERE "
                                                             + "user_login LIKE ? OR "
                                                             + "user_email LIKE ? OR "
                                                             + "user_name LIKE ? OR "
                                                             + "user_surname LIKE ?";


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

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(SET_PHOTO_QUERY)) {

            ps.setString(1, photo);
            ps.setString(2, login);

            int counter = ps.executeUpdate();

            if (counter != 1) {
                throw new PersistException(PERSIST_EXCEPTION);
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        } catch (PersistException e) {
            LOGGER.error(PERSIST_EXCEPTION, e);
            throw new DAOException(PERSIST_EXCEPTION);
        }
    }

    @Override
    protected int preparedStatementForCreate(Connection con, User user, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getSurname());
        ps.setBoolean(5, user.getAdminRoot());
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
        ps.setBoolean(5, user.getAdminRoot());
        ps.setString(6, user.getPhoto());
        ps.setLong(7, user.getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, User user, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(getDeleteQuery());

        ps.setString(1, user.getEmail());

        return ps.executeUpdate();
    }

    @Override
    protected User parseResult(ResultSet rs) throws SQLException {

        return BuilderFactory.getUserBuilder()
            .setId(rs.getLong(USER_ID))
            .setLogin(rs.getString(USER_LOGIN))
            .setPassword(rs.getString(USER_PASSWORD))
            .setEmail(rs.getString(USER_NAME))
            .setName(rs.getString(USER_SURNAME))
            .setSurname(rs.getString(USER_EMAIL))
            .setPhoto(rs.getString(USER_PHOTO))
            .setRoot(rs.getBoolean(USER_ADMIN_ROOT))
            .build();
    }

    @Override
    public User authorisation(String login) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_AUTHORIZATION_QUERY)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Model user = parseResult(rs);

            return (User) user;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        }


    }


    @Override
    public User registration(String login, String password, String email, String name, String surname)
        throws DAOException {

        User user = BuilderFactory.getUserBuilder()
            .setLogin(login)
            .setPassword(password)
            .setEmail(email)
            .setName(name)
            .setSurname(surname)
            .build();

        add(user);

        return user;
    }


    @Override
    public boolean checkLogin(String login) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(CHECK_LOGIN_QUERY)) {
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getString(USER_LOGIN) == null) {
                return true;
            } else {
                return true;
            }

        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        }
    }

    @Override
    public boolean checkEmail(String email) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(CHECK_EMAIL_QUERY)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            rs.next();

            try {
                rs.getString(USER_EMAIL);
                return true;
            } catch (SQLException e) {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        }
    }

    public List<User> getUserList(int page) throws DAOException {

        List<User> list = new ArrayList<>();

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY_BY_LIST)) {

            ps.setInt(1, Number.LIMIT_LIST);
            ps.setInt(2, (page * Number.LIMIT_LIST) - Number.LIMIT_LIST);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = (User) parseResult(rs);
                list.add(user);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
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
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("SQL Exception");
        }
        return Integer.valueOf(count) / Number.LIMIT_LIST;
    }

    public Integer getSerchePage(String value) throws DAOException {

        String count;

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(PAGES_RETURN_SEARCHE_QUERY);
             ) {

            ps.setString(1, "%" + value + "%");
            ps.setString(2, "%" + value + "%");
            ps.setString(3, "%" + value + "%");
            ps.setString(4, "%" + value + "%");

            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getString(USER_FIELDS_COUNT);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("SQL Exception");
        }
        return Integer.valueOf(count) / Number.LIMIT_LIST;
    }


    public List<User> search(String value) throws DAOException {

        String sql = SELECT_QUERY + " WHERE user_login LIKE ? "
                     + "OR user_email LIKE   ? "
                     + "OR user_name LIKE    ? "
                     + "OR user_surname LIKE ?";

        List<User> list = new ArrayList<>();

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + value + "%");
            ps.setString(2, "%" + value + "%");
            ps.setString(3, "%" + value + "%");
            ps.setString(4, "%" + value + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = parseResult(rs);
                list.add(user);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        }

        return list;

    }
}
