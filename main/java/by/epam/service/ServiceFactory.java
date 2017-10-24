package by.epam.service;

import by.epam.service.impl.*;

public class ServiceFactory {

    private static ServiceFactory instance;
    private static AlbumTableService albumInstance;
    private static ArtistTableService artistInstance ;
    private static BonusTableService bonusInstance;
    private static CommentTableService commentInstance;
    private static GenreTableService genreInstance;
    private static GoodTableService goodInstance;
    private static OrderTableService orderTableInstance;
    private static SongTableService songInstance;
    private static OrderUserService orderUserInstance;
    private static UserTableService userInstance;


    private ServiceFactory() {
    }

    public static final ServiceFactory getInstance() {

        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public static AlbumTableService getAlbumService() {

        if (albumInstance == null) {
            albumInstance = new AlbumTableService();
        }
        return albumInstance;
    }

    public static ArtistTableService getArtistService() {

        if (artistInstance == null) {
            artistInstance = new ArtistTableService();
        }
        return artistInstance;
    }

    public static BonusTableService getBonusService() {

        if (bonusInstance == null) {
            bonusInstance = new BonusTableService();
        }
        return bonusInstance;
    }

      public static CommentTableService getCommentService() {

        if (commentInstance == null) {
            commentInstance = new CommentTableService();
        }
        return commentInstance;
    }

      public static GenreTableService getGenreService() {

        if (genreInstance == null) {
            genreInstance = new GenreTableService();
        }
        return genreInstance;
    }

      public static GoodTableService getGoodService() {

        if (goodInstance == null) {
            goodInstance = new GoodTableService();
        }
        return goodInstance;
    }

      public static OrderTableService getOrderTableService() {

        if (orderTableInstance == null) {
            orderTableInstance = new OrderTableService();
        }
        return orderTableInstance;
    }

      public static SongTableService getSongService() {

        if (songInstance == null) {
            songInstance = new SongTableService();
        }
        return songInstance;
    }

      public static OrderUserService getOrderUserService() {

        if (orderUserInstance == null) {
            orderUserInstance = new OrderUserService();
        }
        return orderUserInstance;
    }


      public static UserTableService getUserService() {

        if (userInstance == null) {
            userInstance = new UserTableService();
        }
        return userInstance;
    }



}
