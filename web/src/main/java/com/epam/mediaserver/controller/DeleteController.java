package com.epam.mediaserver.controller;

import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.service.ArtistService;
import com.epam.mediaserver.service.BonusService;
import com.epam.mediaserver.service.CommentService;
import com.epam.mediaserver.service.GenreService;
import com.epam.mediaserver.service.OrderService;
import com.epam.mediaserver.service.SongService;
import com.epam.mediaserver.service.UserService;
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

@Controller
@EnableWebMvc
@RequestMapping("/admin")
public class DeleteController {

    private static final Logger LOGGER = LogManager.getLogger(DeleteController.class);

    @Autowired
    private UserService userService;

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

    @Autowired
    private BonusService bonusService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "genre/{idGenre}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGenre(
        @PathVariable("idGenre")
            Long id) {
        genreService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "artist/{idArtist}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteArtist(
        @PathVariable("idArtist")
            Long id) {
        artistService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "album/{idAlbum}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAlbum(
        @PathVariable("idAlbum")
            Long id) {
        albumService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "song/{idSong}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSong(
        @PathVariable("idSong")
            Long id) {
        songService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "bonus/{idBonus}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBonus(
        @PathVariable("idBonus")
            Long id) {

        bonusService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "user/{idUser}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(
        @PathVariable("idUser")
            Long id) {

        userService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "order/{idOrder}",
        method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(
        @PathVariable("idOrder")
            Long id) {

        orderService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
