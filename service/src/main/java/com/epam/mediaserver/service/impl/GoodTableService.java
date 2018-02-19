package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoodTableService {

    private static final Logger LOGGER = LogManager.getLogger(GoodTableService.class);

    public List<OrderSong> getByOrder(int orderId) throws ServiceException {

        List<OrderSong> goods = null;
        try {
            goods = SqlFactory.getOrderSongDao().getByOrder(orderId);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return goods;
    }

    public void delete(int idGood) throws ServiceException {

        try {
            OrderSong good = (OrderSong) SqlFactory.getOrderSongDao().getById(idGood);
            SqlFactory.getOrderSongDao().delete(good);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

}
