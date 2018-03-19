package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Path;
import com.epam.mediaserver.dao.impl.SqlUserDao;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTableService {

    private static final Logger LOGGER = LogManager.getLogger(UserTableService.class);

    @Autowired
    private SqlUserDao userDao;

    public User signUp(String login, String password, String name, String surname, String email)
        throws  ServiceException {

        if (!Validation.userCheck(login, password, name, surname, email)) {
            LOGGER.info(Error.VALIDATION);
            throw new ServiceException(Error.VALIDATION);
        }

        User account = new User();

        account.setLogin(login);
        account.setPassword(String.valueOf(password.hashCode()));
        account.setName(name);
        account.setSurname(surname);
        account.setEmail(email);

        try {
            add(login, password, name, surname, email);
        } catch (ValidateException e) {
            LOGGER.info(Error.VALIDATION);
            throw new ServiceException(Error.VALIDATION);
        }

        return account;
    }

    public boolean checkLogin(String login) throws ServiceException {

        try {
            if (userDao.checkLogin(login)) {
                return true;
            }
        } catch (DAOException e) {
            LOGGER.info(Error.LOGIN_EXISTS);
            throw new ServiceException(Error.LOGIN_EXISTS);
        }
        return false;

    }

    public boolean checkEmail(String email) throws ServiceException {
        try {
            if (userDao.checkEmail(email)) {
                return true;
            }
        } catch (DAOException e) {
            LOGGER.info(Error.EMAIL_EXISTS);
            throw new ServiceException(Error.EMAIL_EXISTS);
        }
        return false;
    }


    public User signIn(String login, Long password)
        throws ServiceException{

        User account;

        if (!Validation.userCheck(login, password)) {
            LOGGER.info(Error.VALIDATION);
            throw new ServiceException(Error.VALIDATION);
        } else {
            account = getByLogin(login);

            if (!account.getLogin().equals(login) || !(account.getPassword().equals(String.valueOf(password.hashCode())))) {
                LOGGER.info(Error.PASSWORD_INCORRECT);
                throw new ServiceException(Error.PASSWORD_INCORRECT);
            }
        }
        return account;
    }


    public void add(String login, String password, String name, String surname, String email)
        throws ValidateException, ServiceException {

        if (!Validation.userCheck(login, password, name, surname, email)) {
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }

        User account = new User();

        account.setLogin(login);
        account.setPassword(String.valueOf(password.hashCode()));
        account.setName(name);
        account.setSurname(surname);
        account.setEmail(email);
        account.setPhoto(Path.DEFAULT_USER);

        try {
            userDao.add(account);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void addPhoto(String photo, String login) throws ServiceException {

        try {
            userDao.setPhoto(photo, login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }


    public void edit(Long id, String login, String name, String surname, String email, boolean isRoot)
        throws ServiceException, ValidateException {

        User user = getById(id);
        try {
            if (Validation.userCheck(id, login, name, surname, email)) {

                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setEmail(email);
                user.setAdminRoot(isRoot);

                userDao.update(user);

            } else {
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void editPhoto(Long id, String photo) throws ServiceException, ValidateException {

        try {
            User user = userDao.getById(id);
            if (photo != null) {
                user.setId(id);
                user.setPhoto(photo);

                userDao.update(user);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void delete(Long id) throws ServiceException {

        try {
            User user = userDao.getById(id);
            userDao.delete(user);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public User getById(Long id) throws ServiceException {

        User user = null;

        try {
            user = userDao.getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return user;
    }

    public User getByLogin(String login) throws ServiceException {

        User user = null;

        try {
            user = userDao.authorisation(login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {

        List<User> users = null;

        try {
            users = userDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return users;
    }

    public List<User> getList(int page) throws ServiceException {

        List<User> users = null;

        try {
            users = userDao.getUserList(page);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return users;
    }


    public int getPage() throws ServiceException {


        Integer pages;

        try {
            pages = userDao.getPage();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return pages;
    }
}
