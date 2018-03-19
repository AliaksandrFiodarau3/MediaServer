package com.epam.mediaserver.controller;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.impl.AlbumTableService;
import com.epam.mediaserver.service.impl.ArtistTableService;
import com.epam.mediaserver.service.impl.CommentTableService;
import com.epam.mediaserver.service.impl.GenreTableService;
import com.epam.mediaserver.service.impl.GoodTableService;
import com.epam.mediaserver.service.impl.OrderUserService;
import com.epam.mediaserver.service.impl.SongTableService;
import com.epam.mediaserver.service.impl.UserTableService;
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
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpSession;

@Controller
@EnableWebMvc
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

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

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private GoodTableService goodService;

    @Autowired
    private UserTableService userService;

    @RequestMapping()
    public String userPage() {
        return "user";
    }


    @RequestMapping(value = "order")
    public ResponseEntity<Map<String, Set<OrderSong>>> getOrder(HttpSession session) throws ServiceException {

        Order order = (Order) session.getAttribute("order");

        if (Objects.isNull(order)){
            orderUserService.create((User) session.getAttribute(Attribute.ATTRIBUTE_USER));
            order = orderUserService.getOrder();
        }

        Map<String, Set<OrderSong>> orders = new HashMap<>(1);

        orders.put("orders",  orderUserService.getAllGoodsInOrder());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @RequestMapping(value = "genres")
    public ResponseEntity<Map<String, List<Genre>>> getGenres() throws ServiceException {

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists")
    public ResponseEntity<Map<String, List<Artist>>> getArtists(
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Artist>> artists = new HashMap<>(1);

        artists.put("artists", artistService.getByGenre(genreId));

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums")
    public ResponseEntity<Map<String, List<Album>>> getAlbums(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Album>> albums = new HashMap<>(1);

        albums.put("albums", albumService.getByArtist(artistId));

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums/{albumId}/songs")
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

    @RequestMapping(value = "genres/{genreId}/artists/{artistId}/albums/{albumId}/songs/{songId}/comments")
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
    public ResponseEntity<Map<String, List<Song>>> addGood(
        @PathVariable
            Long artistId,
        @PathVariable
            Long genreId,
        @PathVariable
            Long albumId,
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

        Map<String, List<Song>> songs = new HashMap<>(1);

        songs.put("songs", songService.getByAlbum(albumId));

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
