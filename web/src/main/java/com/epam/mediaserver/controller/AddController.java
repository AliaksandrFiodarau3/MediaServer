package com.epam.mediaserver.controller;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.service.ArtistService;
import com.epam.mediaserver.service.BonusService;
import com.epam.mediaserver.service.GenreService;
import com.epam.mediaserver.service.SongService;
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

import java.sql.Time;


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

    @Autowired
    private SongService songService;

    @Autowired
    private BonusService bonusService;

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
    public ResponseEntity<Void> addGenre(
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

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "artist/genre/{genreId}/title/{titleGenre}/description/{descriptionGenre}/image/{imageGenre}",
        method = RequestMethod.PUT)
    public  ResponseEntity<Void> addArtist(
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

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "album/artist/{artistId}/title/{titleAlbum}/year/{yearAlbum}/description/{descriptionGenre}/image/{imageAlbum}",
        method = RequestMethod.PUT)
    public ResponseEntity<Void> addAlbum(
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

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "song/album/{albumId}/title/{titleSong}/duration/{durationSong}/price/{priceSong}",
        method = RequestMethod.PUT)
    public  ResponseEntity<Void> addSong(
        @PathVariable("albumId")
            Long albumId,
        @PathVariable("titleSong")
            String title,
        @PathVariable("durationSong")
            Time duration,
        @PathVariable("priceSong")
            Double price)  {

        songService.create(
            Song.builder()
                .album(albumService.find(albumId))
                .title(title)
                .duration(duration)
                .price(price)
                .build());

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "bonus/title/{titleBonus}/description/{descriptionBonus}/discount/{discountBonus}/code/{codeBonus}",
        method = RequestMethod.PUT)
    public  ResponseEntity<Void> addSong(
        @PathVariable("titleBonus")
            String title,
        @PathVariable("descriptionBonus")
            String description,
        @PathVariable("discountBonus")
            Integer discount,
        @PathVariable("codeBonus")
            String code)  {

        bonusService.create(
            Bonus.builder()
                .title(title)
                .description(description)
                .discount(discount)
                .code(code)
                .build());

        return new ResponseEntity(HttpStatus.OK);
    }

}
