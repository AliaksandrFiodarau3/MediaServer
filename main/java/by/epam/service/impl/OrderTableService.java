package by.epam.service.impl;

import by.epam.bean.Order;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrderTableService {

    private static final Logger LOGGER = Logger.getLogger(OrderTableService.class);

    public void add(double price, String user) throws ValidateException, ServiceException {

        try {
            if (Validation.orderCheck(price, user)) {

                Order order = new Order();

                order.setPrice(price);
                order.setUser(SqlFactory.getUserDao().authorisation(user));

                Date date = new Date(System.currentTimeMillis());
                order.setDate(date);

                Time time = new Time(System.currentTimeMillis());
                order.setTime(time);

                SqlFactory.getOrderDao().add(order);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void edit(int id, double price, String user, int number) throws ServiceException, ValidateException {

        try {
            Order order = (Order) SqlFactory.getOrderDao().getById(id);

            if (Validation.orderCheck(price, user, number)) {

                order.setNumber(number);
                order.setPrice(price);
                order.setUser(SqlFactory.getUserDao().authorisation(user));

                SqlFactory.getOrderDao().update(order);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void delete(int id) throws ServiceException {

        try {
            Order order = (Order) SqlFactory.getOrderDao().getById(id);
            SqlFactory.getOrderDao().delete(order);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public Order getById(int id) throws ServiceException {

        Order order = null;

        try {
            order = (Order) SqlFactory.getOrderDao().getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return order;

    }

    public List<Order> getByUser(int id) throws ServiceException {

        List<Order> orders = null;

        try {
            orders = SqlFactory.getOrderDao().getByUser(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return orders;

    }

    public List<Order> getAll() throws ServiceException {

        List<Order> orders = null;

        try {
            orders = SqlFactory.getOrderDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return orders;
    }
}
