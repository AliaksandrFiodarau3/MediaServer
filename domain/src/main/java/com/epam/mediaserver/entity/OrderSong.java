package com.epam.mediaserver.entity;

import java.util.Objects;

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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OrderSong orderSong = (OrderSong) o;
        return Objects.equals(order, orderSong.order) &&
               Objects.equals(song, orderSong.song);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), order, song);
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
