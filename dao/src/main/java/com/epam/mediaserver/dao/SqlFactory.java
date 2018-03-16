/*
package com.epam.mediaserver.dao;

import com.epam.mediaserver.dao.impl.SqlAlbumDao;
import com.epam.mediaserver.dao.impl.SqlArtistDao;
import com.epam.mediaserver.dao.impl.SqlBonusDao;
import com.epam.mediaserver.dao.impl.SqlBonusKeeperDao;
import com.epam.mediaserver.dao.impl.SqlCommentDao;
import com.epam.mediaserver.dao.impl.SqlGenreDao;
import com.epam.mediaserver.dao.impl.SqlOrderDao;
import com.epam.mediaserver.dao.impl.SqlOrderSongDao;
import com.epam.mediaserver.dao.impl.SqlSongDao;
import com.epam.mediaserver.dao.impl.SqlUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

*/
/**
 * Factory pattern implementation for com.epam.mediaserver.dao layer
 *//*

@Component
public class SqlFactory {

    private ApplicationContext applicationContext;

    private SqlFactory() {
        applicationContext = new AnnotationConfigApplicationContext(
            SqlUserDao.class,
            SqlBonusDao.class,
            SqlBonusKeeperDao.class,
            SqlGenreDao.class,
            SqlAlbumDao.class,

            SqlArtistDao.class,
            SqlSongDao.class,
            SqlCommentDao.class,
            SqlOrderDao.class,
            SqlOrderSongDao.class
        );
    }

    @Autowired
    private SqlUserDao userInstance;

    @Autowired
    private SqlBonusDao bonusInstance;

    @Autowired
    private SqlBonusKeeperDao bonusKeeperInstance;

    @Autowired
    private SqlGenreDao genreInstance;

    @Autowired
    private SqlAlbumDao albumInstance;

    @Autowired
    private SqlArtistDao artistInstance;

    @Autowired
    private SqlSongDao songInstance;

    @Autowired
    private SqlCommentDao commentInstance;

    @Autowired
    private SqlOrderDao orderInstance;

    @Autowired
    private SqlOrderSongDao orderSongInstance;

    @Autowired
    private SqlFactory instance;



    public SqlFactory getInstance() {
        return instance;
    }

    public SqlUserDao getUserDao() {
        return userInstance;
    }

    public SqlBonusDao getBonusDao() {
        return bonusInstance;
    }

    public SqlBonusKeeperDao getBonusKeeperDao() {
        return bonusKeeperInstance;
    }

    public SqlGenreDao getGenreDao() {
        return genreInstance;
    }

    public SqlAlbumDao getAlbumDao() {
        return albumInstance;
    }

    public SqlArtistDao getArtistDao() {
        return artistInstance;
    }

    public SqlSongDao getSongDao() {
        return songInstance;
    }

    public SqlCommentDao getCommentDao() {
        return commentInstance;
    }

    public SqlOrderDao getOrderDao() {
        return orderInstance;
    }

    public SqlOrderSongDao getOrderSongDao() {
        return orderSongInstance;
    }

}
*/
