package com.epam.mediaserver.entity;


import java.util.Objects;

public class Album extends Model {

    private Artist artist;
    private String title;
    private Integer year;
    private String description;
    private String image;


    public Album() {
        super();
    }

    public Album(Artist artist, String title, Integer year, String description, String image) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Album album = (Album) o;
        return Objects.equals(artist, album.artist) &&
               Objects.equals(title, album.title) &&
               Objects.equals(year, album.year) &&
               Objects.equals(description, album.description) &&
               Objects.equals(image, album.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), artist, title, year, description, image);
    }

    @Override
    public String toString() {
        return "Album{" +
               "artist=" + artist +
               ", title='" + title + '\'' +
               ", year=" + year +
               ", description='" + description + '\'' +
               ", image='" + image + '\'' +
               '}';
    }
}
