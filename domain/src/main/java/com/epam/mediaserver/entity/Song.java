package com.epam.mediaserver.entity;

import java.sql.Time;

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
        if (!(o instanceof Song)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Song song = (Song) o;

        if (getPrice() != song.getPrice()) {
            return false;
        }
        if (getAlbum() != null ? !getAlbum().equals(song.getAlbum()) : song.getAlbum() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(song.getTitle()) : song.getTitle() != null) {
            return false;
        }
        return getDuration() != null ? getDuration().equals(song.getDuration()) : song.getDuration() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getAlbum() != null ? getAlbum().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        result = 31 * result + getPrice();
        return result;
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
