package com.epam.mediaserver.controller;

import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class ViewController {

    private static final Logger LOGGER = LogManager.getLogger(ViewController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SongService songService;

    @Autowired
    private BonusService bonusService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.findAll());
        model.setViewName("user");
        return model;
    }

    @RequestMapping()
    public String mainPage() {
        return "home";
    }

    @RequestMapping(value = "admin")
    public String adminPage() {
        return "admin";
    }

   /* @GetMapping(value = "user")
    public String userPage() {
        return "user";
    }*/

  /*  @RequestMapping(value = "user/order")
    public ResponseEntity<Map<String, Set<OrderSong>>> getOrder(HttpSession session) throws ServiceException {

        Order order = (Order) session.getAttribute("order");

        if (Objects.isNull(order)) {
            orderService.create((User) session.getAttribute(Attribute.ATTRIBUTE_USER));
            order = orderService.getOrder();
        }

        Map<String, Set<OrderSong>> orders = new HashMap<>(1);

        orders.put("orders", orderSongService.getAllGoodsInOrder());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }*/

    @RequestMapping(value = {"admin/profile", "user/profile"})
    public ResponseEntity<Map<String, User>> getProfile(HttpSession session) {

        User account = (User) session.getAttribute("user");

        Map<String, User> user = new HashMap<>(1);

        user.put("user", account);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = {"admin/genres", "user/genres"})
    public ResponseEntity<Map<String, List<Genre>>> getGenres() {

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.findAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/genre/{genreId}/artists",
        "user/genre/{genreId}/artists"})
    public ResponseEntity<Map<String, Object>> getArtists(
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, Object> artists = new HashMap<>();

        artists.put("artists", artistService.getByGenre(genreId));
        artists.put("genreId", genreId);

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/artist/{artistId}/albums",
        "user/artist/{artistId}/albums"})

    public ResponseEntity<Map<String, Object>> getAlbums(
        @PathVariable
            long artistId) throws ServiceException {

        System.out.println(artistId);

        Map<String, Object> albums = new HashMap<>();

        albums.put("albums", albumService.getByArtist(artistId));
        albums.put("artistId", artistId);

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/album/{albumId}/songs",
        "user/album/{albumId}/songs"})
    public ResponseEntity<Map<String, Object>> getSongs(
        @PathVariable long albumId) throws ServiceException{

        Map<String, Object> songs = new HashMap<>();

        songs.put("songs", songService.getByAlbum(albumId));
        songs.put("albumId", albumId);

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/song/{songId}/comments",
        "user/song/{songId}/comments"})
    public ResponseEntity<Map<String, List<Comment>>> getComments(
        @PathVariable
            long songId) throws ServiceException {

        Map<String, List<Comment>> comments = new HashMap<>(1);

        comments.put("comments", commentService.getBySong(songId));

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/bonuses")
    public ResponseEntity<Map<String, List<Bonus>>> getBonuses(){

        Map<String, List<Bonus>> bonuses = new HashMap<>(1);

        bonuses.put("bonuses", bonusService.findAll());

        return new ResponseEntity<>(bonuses, HttpStatus.OK);
    }


    @RequestMapping(value = "admin/users")
    public ResponseEntity<Map<String, List<User>>> getUsers(){

        Map<String, List<User>> users = new HashMap<>(1);

        users.put("users", userService.findAll());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/orders")
    public ResponseEntity<Map<String, List<Order>>> getOrders(){

        Map<String, List<Order>> orders = new HashMap<>(1);

        orders.put("orders", orderService.findAll());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
