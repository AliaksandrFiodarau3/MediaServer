package by.epam.service.impl;

import by.epam.bean.*;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.dao.DAOException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class OrderUserService {

    private static final Logger LOGGER = Logger.getLogger(OrderUserService.class);

    DiscountService discount = null;
    Order order = null;
    Set<OrderSong> songSet = new LinkedHashSet<>();

    public void create(User user) {

        Random random = new Random();

        int number = random.nextInt(9999) + 1000;

        Date date = new java.sql.Date(System.currentTimeMillis());

        Time time = new Time(System.currentTimeMillis());

        if (songSet.size() == 0 && order == null) {
            this.order = new Order(number, user, 0, time, date);
        }
    }


    public void addGoodToOrder(Song song) {

        OrderSong good = new OrderSong(order, song);

        songSet.add(good);
    }


    public void remove(Song song) {

        OrderSong good = new OrderSong(order, song);

        songSet.remove(good);

    }

    public void addBonus(Bonus bonus) {

        discount = new DiscountService(bonus);
    }

    public Set<OrderSong> getAllGoodsInOrder() {
        return songSet;
    }

    public Order getOrder() {
        return order;
    }


    public double getPrice(){

        double price = 0;

        for (OrderSong orderSong : songSet) {

            price = price + orderSong.getSong().getPrice();
        }

        if (discount != null){
            price = this.discount.getPrice(price);
        }

        order.setPrice(price);

        return price;
    }

    public void saveOrder() throws ServiceException {

        try {
            SqlFactory.getOrderDao().add(order);

            Order orderNew = SqlFactory.getOrderDao().getByNumber(order.getNumber());

            for (OrderSong orderSong : songSet) {

                orderSong.setOrder(orderNew);

                SqlFactory.getOrderSongDao().add(orderSong);
            }

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void clearOrder() {
        order = null;
        songSet.clear();
    }
}
