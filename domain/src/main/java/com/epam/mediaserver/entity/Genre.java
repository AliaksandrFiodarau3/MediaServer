package com.epam.mediaserver.entity;

import com.epam.mediaserver.constant.Path;

import java.util.Objects;

public class Genre extends Model {

    private String title;
    private String description;
    private String image;

    public Genre() {
        super();
        this.image = Path.DEFAULT_GENRE;
    }

    public Genre(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
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
        Genre genre = (Genre) o;
        return Objects.equals(title, genre.title) &&
               Objects.equals(description, genre.description) &&
               Objects.equals(image, genre.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), title, description, image);
    }

    @Override
    public String toString() {
        return "Genre{" +
               "title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", image='" + image + '\'' +
               ", id=" + id +
               '}';
    }

}
