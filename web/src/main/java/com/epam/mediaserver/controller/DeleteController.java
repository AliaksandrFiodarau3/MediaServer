/*
package com.epam.mediaserver.controller;

import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.impl.AlbumServiceImpl;
import com.epam.mediaserver.service.impl.ArtistServiceImpl;
import com.epam.mediaserver.service.impl.CommentServiceImpl;
import com.epam.mediaserver.service.impl.GenreServiceImpl;
import com.epam.mediaserver.service.impl.OrderSongServiceImpl;
import com.epam.mediaserver.service.impl.OrderUserService;
import com.epam.mediaserver.service.impl.SongServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("/")
public class DeleteController {

    private static final Logger LOGGER = LogManager.getLogger(DeleteController.class);

    @Autowired
    private GenreServiceImpl genreService;

    @Autowired
    private ArtistServiceImpl artistService;

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private OrderSongServiceImpl goodService;

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping(value = "admin/genre/{idGenre}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, List<Genre>>> deleteGenre(
        @PathVariable("idGenre")
            Long id) throws ServiceException {

        try {
            genreService.delete(id);
        } catch (ServiceException e) {
            LOGGER.error("Service Exception");
        }

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/artist/{idArtist}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, List<Artist>>> deleteArtist(
        @PathVariable("idArtist")
            Long id) throws ServiceException {

        try {
            artistService.delete(id);
        } catch (ServiceException e) {
            LOGGER.error("Service Exception");
        }

        Map<String, List<Artist>> artists = new HashMap<>(1);
        artists.put("artists", artistService.getAll());

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }
}
*/
