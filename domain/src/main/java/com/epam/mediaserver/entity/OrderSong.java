package com.epam.mediaserver.entity;

public class OrderSong extends Model {

    private Order order;
    private Song song;

    public OrderSong() {

    }

    public OrderSong(Order order, Song song) {
        this.order = order;
        this.song = song;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderSong)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        OrderSong orderSong = (OrderSong) o;

        if (getOrder() != null ? !getOrder().equals(orderSong.getOrder()) : orderSong.getOrder() != null) {
            return false;
        }
        return getSong() != null ? getSong().equals(orderSong.getSong()) : orderSong.getSong() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + (getSong() != null ? getSong().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderSong{" +
               "id" + id +
               ", order=" + order +
               ", song=" + song +
               '}';
    }
}
