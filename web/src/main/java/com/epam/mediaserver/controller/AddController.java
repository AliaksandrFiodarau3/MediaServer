package com.epam.mediaserver.controller;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.service.ArtistService;
import com.epam.mediaserver.service.GenreService;
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
@RequestMapping("admin")
public class AddController {

    private static final Logger LOGGER = LogManager.getLogger(AddController.class);

    @Autowired
    private GenreService genreService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

 /*@RequestMapping(value = "user/addGood",
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
*/

    @RequestMapping(value = "genre/title/{titleGenre}/description/{descriptionGenre}/image/{imageGenre}",
        method = RequestMethod.PUT)
    public void addGenre(
        @PathVariable("titleGenre")
            String title,
        @PathVariable("descriptionGenre")
            String description,
        @PathVariable("imageGenre")
            String image) {

        genreService.create(
            Genre.builder()
                .title(title)
                .description(description)
                .image(image)
                .build());
    }

    @RequestMapping(value = "artist/genre/{genreId}/title/{titleGenre}/description/{descriptionGenre}/image/{imageGenre}",
        method = RequestMethod.PUT)
    public void addArtist(
        @PathVariable("genreId")
            Long genreId,
        @PathVariable("titleGenre")
            String title,
        @PathVariable("descriptionGenre")
            String description,
        @PathVariable("imageGenre")
            String image)  {

        artistService.create(
            Artist.builder()
                .genre(genreService.find(genreId))
                .title(title)
                .description(description)
                .image(image)
                .build());
    }


    //getNode('titleAlbum').value +'year/'+ getNode('yearAlbum').value +'/description/'+ getNode('descriptionGenre').value + '/image/'+ getNode('imageGenre').value

    @RequestMapping(value = "album/artist/{artistId}/title/{titleAlbum}/year/{yearAlbum}/description/{descriptionGenre}/image/{imageAlbum}",
        method = RequestMethod.PUT)
    public void addAlbum(
        @PathVariable("artistId")
            Long artistId,
        @PathVariable("titleAlbum")
            String title,
        @PathVariable("yearAlbum")
            Integer year,
        @PathVariable("descriptionGenre")
            String description,
        @PathVariable("imageAlbum")
            String image)  {

        albumService.create(
            Album.builder()
                .artist(artistService.find(artistId))
                .title(title)
                .year(year)
                .description(description)
                .image(image)
                .build());
    }
}
