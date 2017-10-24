package by.epam.service.impl;

import by.epam.bean.OrderSong;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.dao.DAOException;
import org.apache.log4j.Logger;

import java.util.List;

public class GoodTableService {

    private static final Logger LOGGER = Logger.getLogger(GoodTableService.class);

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
