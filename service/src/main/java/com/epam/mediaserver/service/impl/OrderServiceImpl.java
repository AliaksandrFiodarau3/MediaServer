package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.OrderDaoImpl;
import com.epam.mediaserver.dao.impl.UserDaoImpl;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private UserDaoImpl userDao;


    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    public void add(double price, String user) throws ValidateException {

        if (Validation.orderCheck(price, user)) {

            orderDao.create(
                Order.builder()
                    .price(price)
                    .user(userDao.authorisation(user))
                    .build());

        } else {
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }
    }

    public void edit(Long id, double price, String user, int number) throws ValidateException {

            Order order = orderDao.find(id);

            if (Validation.orderCheck(price, user, number)) {

                order.setNumber(number);
                order.setPrice(price);
                order.setUser(userDao.authorisation(user));

                orderDao.update(order);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }

    }


    public void delete(Long id) throws ServiceException {

            Order order = orderDao.find(id);
            orderDao.delete(order);
    }

    public Order getById(Long id) {

        return orderDao.find(id);
    }

    public List<Order> getAll() throws ServiceException {

        return orderDao.findAll();
    }
}
