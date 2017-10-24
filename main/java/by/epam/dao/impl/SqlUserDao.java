package by.epam.dao.impl;

import by.epam.bean.Model;
import by.epam.bean.User;
import by.epam.dao.AbstractModelDao;
import by.epam.dao.UserDao;
import by.epam.dao.impl.pool.ConnectionPool;
import by.epam.exeption.dao.ConnectionPoolException;
import by.epam.exeption.dao.DAOException;
import by.epam.exeption.dao.PersistException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserDao extends AbstractModelDao implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(SqlUserDao.class);

    private static final String CREATE_QUERY = "INSERT INTO t_user " +
            "(user_login, user_password, user_name, user_surname,  user_adminRoot,user_email, user_photo) \n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_QUERY = "Select * FROM t_user";
    private static final String SELECT_QUERY_WITH_ID = "Select * FROM t_user WHERE user_id= ?";
    private static final String UPDATE_QUERY =
            "UPDATE t_user SET user_login = ?, user_name = ?, user_surname = ?, user_email= ?, " +
                    " user_adminRoot = ?, user_photo = ? WHERE user_id = ? ;";
    private static final String DELETE_QUERY = "DELETE FROM t_user WHERE user_email=?;";
    private static final String BY_AUTHORIZATION_QUERY = "select * from t_user where user_login=?;";
    private static final String SET_PHOTO_QUERY = "UPDATE t_user SET user_photo = ? WHERE user_login= ? ;";
    private static final String CHECK_LOGIN_QUERY = "SELECT user_login FROM t_user WHERE user_login = ?;";
    private static final String CHECK_EMAIL_QUERY = "SELECT user_email FROM t_user where user_email = ? ;";

    private static final String USER_ID = "user_id";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_NAME = "user_name";
    private static final String USER_SURNAME = "user_surname";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PHOTO = "user_photo";
    private static final String USER_ADMIN_ROOT = "user_adminRoot";

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
    public String getAuthorizationQuery() {
        return BY_AUTHORIZATION_QUERY;
    }

    @Override
    public String setPhotoQuery() {
        return SET_PHOTO_QUERY;
    }

    @Override
    public String getLoginQuery() {
        return CHECK_LOGIN_QUERY;
    }

    @Override
    public String getEmailQuery() {
        return CHECK_EMAIL_QUERY;
    }


    @Override
    public void setPhoto(String photo, String login) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(setPhotoQuery());

            ps.setString(1, photo);
            ps.setString(2, login);

            int counter = ps.executeUpdate();

            if (counter != 1) {
                throw new PersistException(PERSIST_EXEPTION);
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        } catch (PersistException e) {
            LOGGER.error(PERSIST_EXEPTION, e);
            throw new DAOException(PERSIST_EXEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
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

       /* "INSERT INTO t_user " +
                "(user_login, user_password, user_name, user_surname,  user_adminRoot,user_email, user_photo) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";*/
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

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Model user = null;

        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(getAuthorizationQuery());
            ps.setString(1, login);

            rs = ps.executeQuery();
            rs.next();

            user = parseResult(rs);

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


        return (User) user;
    }


    @Override
    public User registration(String login, long password, String email, String name, String surname) throws DAOException {

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
            ps = con.prepareStatement(getLoginQuery());
            ps.setString(1, login);

            rs = ps.executeQuery();
            rs.next();

            if(rs.getString(USER_LOGIN) == null){
                return true;
            }else {
                return true;
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
    }

    @Override
    public boolean checkEmail(String email) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(getEmailQuery());
            ps.setString(1, email);

            rs = ps.executeQuery();
            rs.next();

            try{
                rs.getString(USER_EMAIL);
                return true;
            } catch (SQLException e){
                return false;
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
    }
}

