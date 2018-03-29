package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.AlbumDaoImpl;
import com.epam.mediaserver.dao.impl.SongDaoImpl;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class SongTableService {

    private static final Logger LOGGER = LogManager.getLogger(SongTableService.class);

    @Autowired
    private SongDaoImpl songDao;

    @Autowired
    private AlbumDaoImpl albumDao;


    public Song getById(Long id) throws ServiceException {

        Song song = null;

        try {
            song = songDao.getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return song;
    }


    public List<Song> getByAlbum(Long albumId) throws ServiceException {
        List<Song> songs = null;

        try {
            songs = songDao.getByAlbum(albumId);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return songs;
    }

    public void add(String title, String album, String duration, String price)
        throws ValidateException, ServiceException {

        try {
            if (Validation.songCheck(title, album, duration, price)) {

                Song song = new Song();

                song.setTitle(title);
                song.setAlbum(albumDao.getByName(album));
                song.setPrice(Integer.parseInt(price));
                song.setDuration(Time.valueOf(duration));

                songDao.add(song);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void edit(Long id, String title, String album, String duration, String price)
        throws ServiceException, ValidateException {

        try {
            Song song = songDao.getById(id);

            if (Validation.songCheck(title, album, duration, price)) {

                song.setTitle(title);
                song.setDuration(Time.valueOf(duration));
                song.setPrice(Integer.parseInt(price));
                songDao.update(song);

            } else {
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void delete(Long id) throws ServiceException {

        try {
            Song song = songDao.getById(id);
            songDao.delete(song);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public List<Song> getAll() throws ServiceException {

        List<Song> songs = null;

        try {
            songs = songDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return songs;
    }


}
