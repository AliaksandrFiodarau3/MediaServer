package com.epam.mediaserver.entity;

public class Genre extends Model {

    private String title;
    private String description;
    private String image;

    public Genre() {
        super();
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
        if (!(o instanceof Genre)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Genre genre = (Genre) o;

        if (getTitle() != null ? !getTitle().equals(genre.getTitle()) : genre.getTitle() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(genre.getDescription())
                                     : genre.getDescription() != null) {
            return false;
        }
        return getImage() != null ? getImage().equals(genre.getImage()) : genre.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
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
