package com.epam.mediaserver.entity;

import java.util.Objects;

public class Artist extends Model {

    private Genre genre;
    private String title;
    private String description;
    private String image;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        Artist artist = (Artist) o;
        return Objects.equals(genre, artist.genre) &&
               Objects.equals(title, artist.title) &&
               Objects.equals(description, artist.description) &&
               Objects.equals(image, artist.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), genre, title, description, image);
    }

    @Override
    public String toString() {
        return "Artist{" +
               " id=" + id +
               ", genre=" + genre +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", image='" + image + '\'' +
               '}';
    }
}
