package by.epam.dao;

import by.epam.dao.impl.*;

/**
 * Factory pattern implementation for dao layer
 */

 public class SqlFactory {

    private static SqlUserDao userInstance;
    private static SqlBonusDao bonusInstance;
    private static SqlBonusKeeperDao bonusKeeperInstance;
    private static SqlGenreDao genreInstance;
    private static SqlAlbumDao albumInstance;
    private static SqlArtistDao artistInstance;
    private static SqlSongDao songInstance;
    private static SqlCommentDao commentInstance;
    private static SqlOrderDao orderInstance;
    private static SqlOrderSongDao orderSongInstance;

    private static SqlFactory instance;

    private SqlFactory() {

    }

    public static final SqlFactory getInstance() {
        if (instance == null) {
            instance = new SqlFactory();
        }
        return instance;
    }

    public static SqlUserDao getUserDao() {
        if (userInstance == null) {
            userInstance = new SqlUserDao();
        }
        return userInstance;
    }

    public static SqlBonusDao getBonusDao() {
        if (bonusInstance == null) {
            bonusInstance = new SqlBonusDao();
        }
        return bonusInstance;
    }

    public static SqlBonusKeeperDao getBonusKeeperDao() {

        if (bonusKeeperInstance == null) {
            bonusKeeperInstance = new SqlBonusKeeperDao();
        }

        return bonusKeeperInstance;
    }

    public static SqlGenreDao getGenreDao() {

        if (genreInstance == null) {
            genreInstance = new SqlGenreDao();
        }

        return genreInstance;
    }

    public static SqlAlbumDao getAlbumDao() {

        if (albumInstance == null) {
            albumInstance = new SqlAlbumDao();
        }

        return albumInstance;
    }

    public static SqlArtistDao getArtistDao() {
        if (artistInstance == null) {
            artistInstance = new SqlArtistDao();
        }

        return artistInstance;
    }

    public static SqlSongDao getSongDao() {
        if (songInstance == null) {
            songInstance = new SqlSongDao();
        }
        return songInstance;
    }

    public static SqlCommentDao getCommentDao() {
        if(commentInstance == null){
            commentInstance = new SqlCommentDao();
        }
        return commentInstance;
    }

    public static SqlOrderDao getOrderDao() {

        if(orderInstance == null){
            orderInstance = new SqlOrderDao();
        }

        return orderInstance;
    }

    public static SqlOrderSongDao getOrderSongDao() {

        if(orderSongInstance == null){
            orderSongInstance = new SqlOrderSongDao();
        }

        return orderSongInstance;
    }

}
