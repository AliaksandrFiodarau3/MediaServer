package com.epam.mediaserver.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private static final Logger LOGGER = LogManager.getLogger(ViewController.class);

  /*  private UserService userService;
    private OrderService orderService;
    private GenreService genreService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.findAll());
        model.setViewName("user");
        return model;
    }*/

    @GetMapping(value = "home")
    public String homePage() {
        return "home";
    }

    @GetMapping()
    public String mainPage() {
        return "redirect:home";
    }

    @GetMapping(value = "admin")
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

   /* @RequestMapping(value = {"admin/profile", "user/profile"})
    public ResponseEntity<Map<String, User>> getProfile(HttpSession session) {

        User account = (User) session.getAttribute("user");

        Map<String, User> user = new HashMap<>(1);

        user.put("user", account);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = {"admin/genres", "user/genres"})
    public ResponseEntity<Map<String, List<Genre>>> getGenres() throws ServiceException {

        Map<String, List<Genre>> genres = new HashMap<>(1);
        genres.put("genres", genreService.findAll());

        return new ResponseEntity<>(genres, HttpStatus.OK);
    }*/

   /* @RequestMapping(value = {
        "admin/genres/{genreId}/artists",
        "user/genres/{genreId}/artists"})
    public ResponseEntity<Map<String, List<Artist>>> getArtists(
        @PathVariable
            long genreId) throws ServiceException {

        Map<String, List<Artist>> artists = new HashMap<>(1);

        artists.put("artists", artistService.getByGenre(genreId));

        return new ResponseEntity<>(artists, HttpStatus.OK);
    }*/

    /*@RequestMapping(value = {
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
    }*/

  /*  @RequestMapping(value = {
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
    }*/

  /*  @RequestMapping(value = {
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
    }*/
}
