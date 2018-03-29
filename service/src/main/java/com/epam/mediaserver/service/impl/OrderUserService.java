package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.impl.OrderDaoImpl;
import com.epam.mediaserver.dao.impl.OrderSongDaoImpl;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


@Service
public class OrderUserService {

    private static final Logger LOGGER = LogManager.getLogger(OrderUserService.class);

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private OrderSongDaoImpl orderSongDao;

    DiscountService discount = null;
    Order order = null;
    Set<OrderSong> songSet = new LinkedHashSet<>();

    public void create(User user) {

        int number = new Random().nextInt(9999) + 1000;

        if (songSet.size() == 0 && order == null) {
            this.order = Order.builder()
                .number(number)
                .user(user)
                .build();
        }
    }


    public void addGoodToOrder(Song song) {

        OrderSong good = OrderSong.builder()
            .order(order)
            .song(song)
            .build();

        songSet.add(good);
    }


    public void remove(Song song) {

        OrderSong good = OrderSong.builder()
            .order(order)
            .song(song)
            .build();

        songSet.remove(good);

    }

    public void addBonus(Bonus bonus) {

        discount = new DiscountService();
    }

    public Set<OrderSong> getAllGoodsInOrder() {
        return songSet;
    }

    public Order getOrder() {
        return order;
    }


    public double getPrice() {

        double price = 0;

        for (OrderSong orderSong : songSet) {

            price = price + orderSong.getSong().getPrice();
        }

        if (discount != null) {
            price = this.discount.getPrice(price);
        }

        order.setPrice(price);

        return price;
    }

   /* public void saveOrder() throws ServiceException {

        try {
            orderDao.create(order);

            Order orderNew = orderDao.getByNumber(order.getNumber());

            for (OrderSong orderSong : songSet) {

                orderSong.setOrder(orderNew);

                orderSongDao.add(orderSong);
            }

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }*/

    public void clearOrder() {
        order = null;
        songSet.clear();
    }
}
