package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.entity.Bonus;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private Bonus bonus = new Bonus();

    public DiscountService() {
    }

    public double getPrice(double price) {

        double discount = price * bonus.getDiscount() / 100;

        double cost = price - discount;

        return cost;
    }

}
