package by.epam.service.impl;

import by.epam.bean.User;
import by.epam.constant.Error;
import by.epam.constant.Path;
import by.epam.dao.SqlFactory;
import by.epam.exeption.PasswordIncorrectException;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.util.List;

public class UserTableService {

    private static final Logger LOGGER = Logger.getLogger(UserTableService.class);


    public User signUp(String login, String password, String name, String surname, String email) throws ValidateException, ServiceException {

        if (!Validation.userCheck(login, password, name, surname, email)) {
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }

        User account = new User();

        account.setLogin(login);
        account.setPassword(password.hashCode());
        account.setName(name);
        account.setSurname(surname);
        account.setEmail(email);

        add(login, password, name, surname, email);

        return account;
    }

    public boolean checkLogin(String login) throws ServiceException {

        try {
            if (SqlFactory.getUserDao().checkLogin(login)) {
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
            if (SqlFactory.getUserDao().checkEmail(email)) {
                return true;
            }
        } catch (DAOException e) {
            LOGGER.info(Error.EMAIL_EXISTS);
            throw new ServiceException(Error.EMAIL_EXISTS);
        }
        return false;
    }


    public User signIn(String login, String password) throws ServiceException, ValidateException, PasswordIncorrectException {

        User account;

            if (!Validation.userCheck(login, password)) {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            } else {
                account = getByLogin(login);
                if (!account.getLogin().equals(login) || !(account.getPassword() == password.hashCode())) {
                    LOGGER.info(Error.PASSWORD_INCORRECT);
                    throw new PasswordIncorrectException(Error.PASSWORD_INCORRECT);
                }
            }
        return account;
    }


    public void add(String login, String password, String name, String surname, String email) throws ValidateException, ServiceException {

        if (!Validation.userCheck(login, password, name, surname, email)) {
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }

        User account = new User();

        account.setLogin(login);
        account.setPassword(password.hashCode());
        account.setName(name);
        account.setSurname(surname);
        account.setEmail(email);
        account.setPhoto(Path.DEFAULT_USER);

        try {
            SqlFactory.getUserDao().add(account);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void addPhoto(String photo, String login) throws ServiceException {

        try {
            SqlFactory.getUserDao().setPhoto(photo, login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }


    public void edit(int id, String login, String name, String surname, String email, boolean isRoot) throws ServiceException, ValidateException {

        User user = ServiceFactory.getUserService().getById(id);
        try {
            if (Validation.userCheck(id, login, name, surname, email)) {

                user.setName(name);
                user.setSurname(surname);
                user.setLogin(login);
                user.setEmail(email);
                user.setAdminRoot(isRoot);

                SqlFactory.getUserDao().update(user);

            } else {
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void editPhoto(int id, String photo) throws ServiceException, ValidateException {

        User user = null;
        try {
            user = (User) SqlFactory.getUserDao().getById(id);
            if (photo != null) {
                user.setId(id);
                user.setPhoto(photo);

                SqlFactory.getUserDao().update(user);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void delete(int id) throws ServiceException {

        try {
            User user = (User) SqlFactory.getUserDao().getById(id);
            SqlFactory.getUserDao().delete(user);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public User getById(int id) throws ServiceException {

        User user = null;

        try {
            user = (User) SqlFactory.getUserDao().getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return user;
    }

    public User getByLogin(String login) throws ServiceException {

        User user = null;

        try {
            user = SqlFactory.getUserDao().authorisation(login);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return user;
    }

    public List<User> getAll() throws ServiceException {

        List<User> users = null;

        try {
            users = SqlFactory.getUserDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return users;
    }

}
