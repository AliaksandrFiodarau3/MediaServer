package com.epam.mediaserver.controller;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.impl.AlbumServiceImpl;
import com.epam.mediaserver.service.impl.ArtistServiceImpl;
import com.epam.mediaserver.service.impl.CommentServiceImpl;
import com.epam.mediaserver.service.impl.GenreServiceImpl;
import com.epam.mediaserver.service.impl.OrderSongServiceImpl;
import com.epam.mediaserver.service.impl.OrderUserService;
import com.epam.mediaserver.service.impl.SongServiceImpl;
import com.epam.mediaserver.service.impl.UserServiceImpl;
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
import java.util.Set;
import javax.servlet.http.HttpSession;


@Controller
@EnableWebMvc
@RequestMapping("/")
public class AddController {

    private static final Logger LOGGER = LogManager.getLogger(AddController.class);

    @RequestMapping(value = "user/addGood",
        method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Set<OrderSong>>> addGood(
        @PathVariable
            Long songId,
        HttpSession session
    ) throws ServiceException {

        User account = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);

        User user = userService.getByLogin(account.getLogin());
        orderUserService.create(user);

        Song song = songService.getById(songId);

        orderUserService.addGoodToOrder(song);
        orderUserService.getPrice();

        session.setAttribute("order", orderUserService.getOrder());
        session.setAttribute("orderSongs", orderUserService.getAllGoodsInOrder());

        Map<String, Set<OrderSong>> songs = new HashMap<>(1);

        songs.put("songs", orderUserService.getAllGoodsInOrder());

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/genre/title/{titleGenre}/description/{descriptionGenre}/image/{imageGenre}",
        method = RequestMethod.PUT)
    public ResponseEntity<Map<String, List<Genre>>> addGenre(
        @PathVariable("titleGenre")
            String title,
        @PathVariable("descriptionGenre")
            String description,
        @PathVariable("imageGenre")
            String image) throws ServiceException {

        try {
            genreService.add(title, description, image);
        } catch (ValidateException e) {
            LOGGER.info("Error validation");
        } catch (ServiceException e) {
            LOGGER.error("Service Exception");
        }

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/artist/title/{titleGenre}/description/{descriptionGenre}/image/{imageGenre}",
        method = RequestMethod.PUT)
    public ResponseEntity<Map<String, List<Genre>>> addArtist(
        @PathVariable("titleGenre")
            String title,
        @PathVariable("descriptionGenre")
            String description,
        @PathVariable("imageGenre")
            String image) throws ServiceException {

        try {
            genreService.add(title, description, image);
        } catch (ValidateException e) {
            LOGGER.info("Error validation");
        } catch (ServiceException e) {
            LOGGER.error("Service Exception");
        }

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

}