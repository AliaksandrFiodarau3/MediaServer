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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService, UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

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

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String login)
        throws UsernameNotFoundException {

        try {
            User account = userDao.findByLogin(login);

            return new org.springframework.security.core.userdetails.User(
                account.getLogin(),account.getPassword(),getAuthority());

        } catch (DAOException e) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }
}
