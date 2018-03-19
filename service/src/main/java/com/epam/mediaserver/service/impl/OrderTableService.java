package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.SqlOrderDao;
import com.epam.mediaserver.dao.impl.SqlUserDao;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class OrderTableService {

    @Autowired
    private SqlOrderDao orderDao;

    @Autowired
    private SqlUserDao userDao;


    private static final Logger LOGGER = LogManager.getLogger(OrderTableService.class);

    public void add(double price, String user) throws ValidateException, ServiceException {

        try {
            if (Validation.orderCheck(price, user)) {

                Order order = new Order();

                order.setPrice(price);
                order.setUser(userDao.authorisation(user));

                Date date = new Date(System.currentTimeMillis());
                order.setDate(date);

                Time time = new Time(System.currentTimeMillis());
                order.setTime(time);

                orderDao.add(order);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void edit(Long id, double price, String user, int number) throws ServiceException, ValidateException {

        try {
            Order order = orderDao.getById(id);

            if (Validation.orderCheck(price, user, number)) {

                order.setNumber(number);
                order.setPrice(price);
                order.setUser(userDao.authorisation(user));

                orderDao.update(order);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void delete(Long id) throws ServiceException {

        try {
            Order order = orderDao.getById(id);
            orderDao.delete(order);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public Order getById(Long id) throws ServiceException {

        Order order = null;

        try {
            order = orderDao.getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return order;

    }

    public List<Order> getByUser(int id) throws ServiceException {

        List<Order> orders = null;

        try {
            orders = orderDao.getByUser(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return orders;

    }

    public List<Order> getAll() throws ServiceException {

        List<Order> orders = null;

        try {
            orders = orderDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return orders;
    }
}
