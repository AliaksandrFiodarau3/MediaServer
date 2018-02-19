package by.epam.service.impl;

import by.epam.bean.Song;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.sql.Time;
import java.util.List;

public class SongTableService {

    private static final Logger LOGGER = Logger.getLogger(SongTableService.class);

    public Song getById(int id) throws ServiceException {

        Song song = null;

        try {
            song = (Song) SqlFactory.getSongDao().getById(id);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return song;
    }


    public List<Song> getByAlbum(String album) throws ServiceException {
        List<Song> songs = null;

        try {
            songs = SqlFactory.getSongDao().getByAlbum(album);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return songs;
    }

    public void add(String title, String album, String duration, String price) throws ValidateException, ServiceException {

        try {
            if (Validation.songCheck(title, album, duration, price)) {

                Song song = new Song();

                song.setTitle(title);
                song.setAlbum(SqlFactory.getAlbumDao().getByName(album));
                song.setPrice(Integer.parseInt(price));
                song.setDuration(Time.valueOf(duration));

                SqlFactory.getSongDao().add(song);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public void edit(int id, String title, String album, String duration, String price) throws ServiceException, ValidateException {

        try {
            Song song = (Song) SqlFactory.getSongDao().getById(id);

            if (Validation.songCheck(title, album, duration, price)) {

                song.setTitle(title);
                song.setDuration(Time.valueOf(duration));
                song.setPrice(Integer.parseInt(price));
                SqlFactory.getSongDao().update(song);

            } else {
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void delete(int id) throws ServiceException {

        try {
            Song song = (Song) SqlFactory.getSongDao().getById(id);
            SqlFactory.getSongDao().delete(song);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public List<Song> getAll() throws ServiceException {

        List<Song> songs = null;

        try {
            songs = SqlFactory.getSongDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return songs;
    }



}
