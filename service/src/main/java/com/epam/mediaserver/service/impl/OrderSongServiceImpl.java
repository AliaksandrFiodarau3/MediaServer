/*
package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.OrderSongDao;
import com.epam.mediaserver.service.OrderSongService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSongServiceImpl extends CrudServiceImpl<OrderSong, Long> implements OrderSongService {

    private static final Logger LOGGER = LogManager.getLogger(OrderSongServiceImpl.class);

    OrderSongServiceImpl(OrderSongDao orderSongDao) {
        super(orderSongDao);
    }

    public List<OrderSong> getByOrder(Long orderId)  {

        return ((OrderSongDao)getDao()).getByOrder(orderId);
    }

}
*/
