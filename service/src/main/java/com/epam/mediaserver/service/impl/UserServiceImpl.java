package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.UserDao;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.UserService;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    UserServiceImpl(UserDao dao) {
        super(dao);
    }

    @Override
    public User signUp(User account) throws ServiceException {
        try {
            (getDao()).create(account);
        } catch (Exception e) {
            throw new ServiceException("Service exception");
        }
        return account;
    }

    public boolean checkLogin(String login) throws ServiceException {

        try {
            if (((UserDao) getDao()).checkLogin(login)) {
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
            if (((UserDao) getDao()).checkEmail(email)) {
                return true;
            }
        } catch (DAOException e) {
            LOGGER.info(Error.EMAIL_EXISTS);
            throw new ServiceException(Error.EMAIL_EXISTS);
        }
        return false;
    }

    @Override
    public User signIn(String login, Long password)
        throws ServiceException {

        User account;

        if (!Validation.userCheck(login, password)) {
            LOGGER.info(Error.VALIDATION);
            throw new ServiceException(Error.VALIDATION);
        } else {
            account = getByLogin(login);

            if (!account.getLogin().equals(login) || !(account.getPassword()).equals(String.valueOf(password))) {
                LOGGER.info(Error.PASSWORD_INCORRECT);
                throw new ServiceException(Error.PASSWORD_INCORRECT);

            }
        }
        return account;
    }

    public void addPhoto(String photo, String login) throws ServiceException {

        try {
            ((UserDao) getDao()).setPhoto(photo, login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public User getByLogin(String login) throws ServiceException {

        try {
            return ((UserDao) getDao()).authorisation(login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }
}
