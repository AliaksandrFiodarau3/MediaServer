/*
package com.epam.mediaserver.service.impl;

import static junit.framework.TestCase.assertTrue;

import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

    int number = 100500;
    String userLogin = "Admin";
    double price = 0;
    boolean adminRoot = true;
    private ConnectionPool connectionPool;

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String userLogin, double price) throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getOrderTableService().add(price, userLogin);
    }


    public void edit(int id, int number, String userLogin, double price) throws ServiceException, ValidateException {

        ServiceFactory.getOrderTableService().edit(id, price, userLogin, number);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getGenreService().delete(id);
    }

    public boolean check(int id, int number, User user, double price) throws DAOException {

        Order order = (Order) SqlFactory.getOrderDao().getById(id);

        if (order == null) {
            return false;
        }

        if (order.getNumber() == number && order.getUser().equals(user) && order.getPrice() == price) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {

        add(userLogin, price);

        User user = SqlFactory.getUserDao().authorisation(userLogin);

        Order order = (Order) SqlFactory.getOrderDao().getByUser(user.getId());
        int id = order.getId();

        boolean isAdd = check(id, number, user, price);

        delete(id);

        boolean isDelete = check(id, number, user, price);

        boolean finalTest = false;

        if (isAdd && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
*/
