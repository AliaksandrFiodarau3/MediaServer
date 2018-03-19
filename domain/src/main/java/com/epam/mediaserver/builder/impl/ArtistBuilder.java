package com.epam.mediaserver.builder.impl;

import com.epam.mediaserver.builder.Builder;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;

public class ArtistBuilder implements Builder<Artist> {

    private Artist artist;

    public ArtistBuilder() {
        artist = new Artist();
        artist.setId(null);
        artist.setTitle("EMPTY");
        artist.setDescription("EMPTY");
        artist.setImage("EMPTY");
        artist.setGenre(new Genre());
    }

    public ArtistBuilder setId(Long id) {
        artist.setId(id);
        return this;
    }

    public ArtistBuilder setGenre(Genre genre) {
        artist.setGenre(genre);
        return this;
    }

    public ArtistBuilder setTitle(String title) {
        artist.setTitle(title);
        return this;
    }

    public ArtistBuilder setDescription(String description) {
        artist.setDescription(description);
        return this;
    }

    public ArtistBuilder setImage(String image) {
        artist.setImage(image);
        return this;
    }

    @Override
    public Artist build() {
        return artist;
    }
}
