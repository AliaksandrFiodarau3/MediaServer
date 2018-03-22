package com.epam.mediaserver.controller;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpSession;

@Controller
@EnableWebMvc
@RequestMapping("/")
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

    @RequestMapping(method = RequestMethod.POST,
        value = "login")
    public RedirectView signIn(
        @RequestParam("loginUser")
            String login,
        @RequestParam("passwordUser")
            String password,
        HttpSession session) {
        System.out.println("LOGIN!");
        try {
            User account = userService.signIn(login, (long) password.hashCode());
            session.setAttribute(Attribute.ATTRIBUTE_USER, account);

            if (account.getAdminRoot()) {
                return new RedirectView("admin");
            } else {
                return new RedirectView("user");
            }

        } catch (ServiceException e) {
            LOGGER.info(Message.AUTHORIZATION_ERROR, e);
            return new RedirectView("home");
        }
    }

    @RequestMapping(method = RequestMethod.POST,
        value = "registration")
    public String signUp(
        @ModelAttribute("loginUser")
            String login,
        @ModelAttribute("passwordUser")
            String password,
        @ModelAttribute("nameUser")
            String name,
        @ModelAttribute("surnameUser")
            String surname,
        @ModelAttribute("emailUser")
            String email,
        HttpSession session) {

        try {
            session.setAttribute(Attribute.ATTRIBUTE_USER, userService.signUp(login, password, name, surname, email));
        } catch (ServiceException e) {
            LOGGER.info(Message.CLIENT_SERVICE_ERROR);
            return "redirect:home";
        }
        return "redirect:user";
    }

    @RequestMapping()
    public String homePage() {
        return "home";
    }


    @RequestMapping(value = "signOut")
    public String signOut(HttpSession session) {

        session.setAttribute(Attribute.ATTRIBUTE_USER, null);

        return "redirect:/";
    }

    @RequestMapping(value = "admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "user")
    public String userPage() {
        return "user";
    }


    @RequestMapping(value = "user/order")
    public ResponseEntity<Map<String, Set<OrderSong>>> getOrder(HttpSession session) throws ServiceException {

        Order order = (Order) session.getAttribute("order");

        if (Objects.isNull(order)) {
            orderUserService.create((User) session.getAttribute(Attribute.ATTRIBUTE_USER));
            order = orderUserService.getOrder();
        }

        Map<String, Set<OrderSong>> orders = new HashMap<>(1);

        orders.put("orders", orderUserService.getAllGoodsInOrder());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = {"admin/profile", "user/profile"})
    public ResponseEntity<Map<String, User>> getProfile(HttpSession session) {

        User account = (User) session.getAttribute("user");

        Map<String, User> user = new HashMap<>(1);

        user.put("user", account);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = {"admin/genres", "user/genres"})
    public ResponseEntity<Map<String, List<Genre>>> getGenres() throws ServiceException {

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.getAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/genres/{genreId}/artists",
        "user/genres/{genreId}/artists"})
    public ResponseEntity<Map<String, List<Artist>>> getArtists(
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Artist>> artists = new HashMap<>(1);

        artists.put("artists", artistService.getByGenre(genreId));

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @RequestMapping(value = {
        "admin/genres/{genreId}/artists/{artistId}/albums",
        "user/genres/{genreId}/artists/{artistId}/albums"})
    public ResponseEntity<Map<String, List<Album>>> getAlbums(
        @PathVariable
            long artistId,
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Album>> albums = new HashMap<>(1);

        albums.put("albums", albumService.getByArtist(artistId));

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @RequestMapping(value = {
                 "admin/genres/{genreId}/artists/{artistId}/albums/{albumId}/songs",
                 "user/genres/{genreId}/artists/{artistId}/albums/{albumId}/songs"})
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

    @RequestMapping(value = {
         "admin/genres/{genreId}/artists/{artistId}/albums/{albumId}/songs/{songId}/comments",
         "user/genres/{genreId}/artists/{artistId}/albums/{albumId}/songs/{songId}/comments"})
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

    @RequestMapping(value = "user/addGood", method = RequestMethod.PUT)
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

}
