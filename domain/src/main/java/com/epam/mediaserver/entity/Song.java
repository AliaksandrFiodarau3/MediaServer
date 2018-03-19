package com.epam.mediaserver.entity;

import java.sql.Time;
import java.util.Objects;

public class Song extends Model {

    private Album album;
    private String title;
    private Time duration;
    private int price;


    public Song() {
    }

    public Song(Album album, String title, Time duration, int price) {
        this.album = album;
        this.title = title;
        this.duration = duration;
        this.price = price;
    }


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        Song song = (Song) o;
        return price == song.price &&
               Objects.equals(album, song.album) &&
               Objects.equals(title, song.title) &&
               Objects.equals(duration, song.duration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), album, title, duration, price);
    }

    @Override
    public String toString() {
        return "Song{ id= " + id +
               ", album=" + album +
               ", title='" + title + '\'' +
               ", duration=" + duration +
               ", price=" + price +
               '}';
    }
}
