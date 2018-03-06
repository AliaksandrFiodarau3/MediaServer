package com.epam.mediaserver.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Order extends Model {

    private int number;
    private User user;
    private double price;
    private Time time;
    private Date date;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Order order = (Order) o;
        return number == order.number &&
               Double.compare(order.price, price) == 0 &&
               Objects.equals(user, order.user) &&
               Objects.equals(time, order.time) &&
               Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), number, user, price, time, date);
    }

    @Override
    public String toString() {
        return "Order{" +
               "number=" + number +
               ", user=" + user +
               ", price=" + price +
               ", time=" + time +
               ", date=" + date +
               '}';
    }
}
