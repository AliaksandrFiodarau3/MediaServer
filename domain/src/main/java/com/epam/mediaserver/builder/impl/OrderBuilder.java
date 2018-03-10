package com.epam.mediaserver.builder.impl;


import com.epam.mediaserver.builder.Builder;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.User;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderBuilder implements Builder<Order> {

    private Order order;

    public OrderBuilder() {
        order = new Order();
        order.setId(null);
        order.setNumber(0);
        order.setUser(new User());
        order.setPrice(0);


        order.setDate(Date.valueOf(LocalDate.now()));


        order.setTime(Time.valueOf(LocalTime.now()));
    }

    public OrderBuilder setId(Long id){
        order.setId(id);
        return this;
    }

    public OrderBuilder setNumber(int number){
        order.setNumber(0);
        return this;
    }

    public OrderBuilder setUser(User user) {
        order.setUser(user);
        return this;
    }

    public OrderBuilder setPrice(double price) {
        order.setPrice(price);
        return this;
    }

    public OrderBuilder setData(Date data){
        order.setDate(data);
        return this;
    }

    public OrderBuilder setTime(Time time){
        order.setTime(time);
        return this;
    }

    @Override
    public Order build() {
        return order;
    }
}
