package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.SqlOrderSongDao;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodTableService {

    private static final Logger LOGGER = LogManager.getLogger(GoodTableService.class);

    @Autowired
    private SqlOrderSongDao orderSongDao;


    public List<OrderSong> getByOrder(int orderId) throws ServiceException {

        List<OrderSong> goods = null;
        try {
            goods = orderSongDao.getByOrder(orderId);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
        return goods;
    }

    public void delete(int idGood) throws ServiceException {

        try {
            OrderSong good = orderSongDao.getById(idGood);
            orderSongDao.delete(good);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

}
