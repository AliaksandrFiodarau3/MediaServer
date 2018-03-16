package com.epam.mediaserver.entity;


public class Album extends Model {

    private Artist artist;
    private String title;
    private int year;
    private String description;
    private String image;


    public Album() {
        super();
    }

    public Album(Artist artist, String title, int year, String description, String image) {
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

    public int getYear() {
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
        if (!(o instanceof Album)) {
            return false;
        }

        Album album = (Album) o;

        if (getYear() != album.getYear()) {
            return false;
        }
        if (getArtist() != null ? !getArtist().equals(album.getArtist()) : album.getArtist() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(album.getTitle()) : album.getTitle() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(album.getDescription())
                                     : album.getDescription() != null) {
            return false;
        }
        return getImage() != null ? getImage().equals(album.getImage()) : album.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getArtist() != null ? getArtist().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
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
