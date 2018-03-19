package com.epam.mediaserver.controller;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.impl.AlbumTableService;
import com.epam.mediaserver.service.impl.ArtistTableService;
import com.epam.mediaserver.service.impl.CommentTableService;
import com.epam.mediaserver.service.impl.GenreTableService;
import com.epam.mediaserver.service.impl.SongTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("user")
public class UserController {

    @Autowired
    private GenreTableService genreService;

    @Autowired
    private ArtistTableService artistService;

    @Autowired
    private AlbumTableService albumService;

    @Autowired
    private SongTableService songService;

    @Autowired
    private CommentTableService commentService;

    @RequestMapping()
    public String userPage() {
        return "user";
    }

    @RequestMapping(value = "genres",
        method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Genre>>> getGenres() throws ServiceException {

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists",
        method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Artist>>> getArtists(
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Artist>> artists = new HashMap<>(1);
        artists.put("artists", artistService.getByGenre(genreId));

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums",
        method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Album>>> getAlbums(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Album>> albums = new HashMap<>(1);

        albums.put("albums", albumService.getByArtist(artistId));

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums/{albumId}/songs",
        method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Song>>> getSongs(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId,
        @PathVariable
            long albumId) throws ServiceException {

        Map<String, List<Song>> songs = new HashMap<>(1);

        songs.put("songs", songService.getByAlbum(albumId));

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums/{albumId}/songs/{songId}/comments",
        method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Comment>>> getComments(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId,
        @PathVariable
            long albumId,
        @PathVariable
            long songId) throws ServiceException {

        Map<String, List<Comment>> comments = new HashMap<>(1);

        comments.put("comments", commentService.getBySong(songId));

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums/{albumId}/songs/{songId}/addGood",
        method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<Comment>>> addGood(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId,
        @PathVariable
            long albumId,
        @PathVariable
            long songId) throws ServiceException {

        Map<String, List<Comment>> comments = new HashMap<>(1);

        comments.put("comments", commentService.getBySong(songId));

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
