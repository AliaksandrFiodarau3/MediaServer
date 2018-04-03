package com.epam.mediaserver.controller;

import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.service.ArtistService;
import com.epam.mediaserver.service.CommentService;
import com.epam.mediaserver.service.GenreService;
import com.epam.mediaserver.service.SongService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class DeleteController {

    private static final Logger LOGGER = LogManager.getLogger(DeleteController.class);

    @Autowired
    private GenreService genreService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "admin/genre/{idGenre}",
        method = RequestMethod.DELETE)
    public void deleteGenre(
        @PathVariable("idGenre")
            Long id) {
        genreService.delete(id);
    }

    @RequestMapping(value = "admin/artist/{idArtist}",
        method = RequestMethod.DELETE)
    public void deleteArtist(
        @PathVariable("idArtist")
            Long id) {
        artistService.delete(id);
    }

    @RequestMapping(value = "admin/album/{idAlbum}",
        method = RequestMethod.DELETE)
    public void deleteAlbum(
        @PathVariable("idAlbum")
            Long id) {
        albumService.delete(id);
    }
}
