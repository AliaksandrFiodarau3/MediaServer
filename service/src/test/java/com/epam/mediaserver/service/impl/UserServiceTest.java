package com.epam.mediaserver.service.impl;

import static junit.framework.TestCase.assertTrue;

import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ServiceFactory;

import org.junit.BeforeClass;
import org.junit.Test;


public class UserServiceTest {

    String login = "Login";
    String name = "Name";
    String surname = "Surname";
    String password = "Qwerty1234";
    String email = "Email@gmail.com";
    String photo = "Photo";
    boolean adminRoot = true;
    private ConnectionPool connectionPool;


    @BeforeClass
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String login, String password, String name, String surname, String email)
        throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getUserService().add(login, password, name, surname, email);
    }


    public void edit(int id, String login, String name, String surname, String email, boolean adminRoot)
        throws ServiceException, ValidateException {

        ServiceFactory.getUserService().edit(id, login, name, surname, email, adminRoot);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getUserService().delete(id);
    }

    public boolean check(int id, String login, String password, String name, String surname, String email,
                         boolean adminRoot) throws DAOException {

        User user = (User) SqlFactory.getUserDao().getById(id);

        if (user == null) {
            return false;
        }
        if (user.getLogin().equals(login) && user.getName().equals(name) && user.getEmail().equals(email)
            && user.getSurname().equals(surname) && user.getPassword() == password.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {

        add(login, password, name, surname, email);

        User user = SqlFactory.getUserDao().authorisation(login);
        int id = user.getId();

        boolean isAdd = check(id, login, password, name, surname, email, adminRoot);

        name = "NewName";

        edit(id, login, name, surname, email, adminRoot);

        boolean isEdit = check(id, login, password, name, surname, email, adminRoot);

        delete(id);

        boolean isDelete = check(id, login, password, name, surname, email, adminRoot);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
