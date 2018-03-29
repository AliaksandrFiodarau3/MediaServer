package com.epam.mediaserver.dao;


import com.epam.mediaserver.entity.OrderSong;

import java.util.List;

public interface OrderSongDao extends CrudDao<OrderSong, Long>{
    List<OrderSong> getByOrder(Long orderId);
}
