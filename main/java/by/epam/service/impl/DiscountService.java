package by.epam.service.impl;

import by.epam.bean.Bonus;

public class DiscountService {

    Bonus bonus = null;

    public DiscountService(Bonus bonus) {
        this.bonus = bonus;
    }

    public double getPrice(double price){

        double discount = price * bonus.getDiscount() / 100;

        double cost = price - discount;

        return cost;
    }

}
