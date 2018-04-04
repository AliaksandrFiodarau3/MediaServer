package com.epam.mediaserver.dao.impl;


import com.epam.mediaserver.dao.UserDao;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository
public class UserDaoImpl extends AbstractModelDao<User, Long> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

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

    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public void setPhoto(String photo, String login) {

    }

    @Override
    public User authorisation(String login) {
        return null;
    }

    @Override
    public User registration(String login, String password, String email, String name, String surname) {
        return null;
    }

    @Override
    public User findByLogin(String login) throws DAOException {

        Query query = getEntityManager().createQuery("select p FROM User p WHERE p.login = ?1", User.class);
        query.setParameter(1, login);

        return (User) query.getSingleResult();
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }

    @Override
    public Long getKey(User entity) {
        return null;
    }
}

