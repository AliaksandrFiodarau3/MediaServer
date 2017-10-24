package by.epam.bean;

import java.sql.Date;
import java.sql.Time;

public class Order extends Model {

    private int number;
    private User user;
    private double price;
    private Time time;
    private Date date;

    public Order() {
    }

    public Order(int number, User user, double price, Time time, Date date) {
        this.number = number;
        this.user = user;
        this.price = price;
        this.time = time;
        this.date = date;
    }

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
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (getNumber() != order.getNumber()) return false;
        if (getUser() != null ? !getUser().equals(order.getUser()) : order.getUser() != null) return false;
        if (getTime() != null ? !getTime().equals(order.getTime()) : order.getTime() != null) return false;
        return getDate() != null ? getDate().equals(order.getDate()) : order.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getNumber();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
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
