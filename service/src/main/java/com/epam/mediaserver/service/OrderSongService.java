package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.OrderSong;

import java.util.List;

public interface OrderSongService {

    public List<OrderSong> getByOrder(Long orderId);

}
