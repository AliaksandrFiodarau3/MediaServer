package by.epam.bean;

public class Artist extends Model {

    private Genre genre;
    private String title;
    private String description;
    private String image;

    public Artist() {
        super();
    }

    public Artist(Genre genre, String title, String description, String image) {
        this.genre = genre;
        this.title = title;
        this.description = description;
        this.image = image;
    }

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
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        if (!super.equals(o)) return false;

        Artist artist = (Artist) o;

        if (getGenre() != null ? !getGenre().equals(artist.getGenre()) : artist.getGenre() != null) return false;
        if (getTitle() != null ? !getTitle().equals(artist.getTitle()) : artist.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(artist.getDescription()) : artist.getDescription() != null)
            return false;
        return getImage() != null ? getImage().equals(artist.getImage()) : artist.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
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
