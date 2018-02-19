package by.epam.service.impl;

import by.epam.bean.Album;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.util.List;

public class AlbumTableService {

    private static final Logger LOGGER = Logger.getLogger(AlbumTableService.class);


    public Album getByName(String title) throws ServiceException {

        Album album = null;

        try {
            album = SqlFactory.getAlbumDao().getByName(title);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return album;
    }


    public List<Album> getByArtist(String artist) throws ServiceException {

        List<Album> albums = null;

        try {
            albums = SqlFactory.getAlbumDao().getByArtist(artist);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return albums;
    }

    public void add(String artist, String title, String year, String description, String image) throws ValidateException, ServiceException {
        try {
            if (Validation.albumCheck(title, year, description, image)) {
                Album album = new Album();
                album.setArtist(SqlFactory.getArtistDao().getByName(artist));
                album.setTitle(title);
                album.setYear(Integer.parseInt(year));
                album.setDescription(description);
                album.setImage(image);
                SqlFactory.getAlbumDao().add(album);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }


    public void edit(int id, String title, String artist,String year, String description, String image) throws ServiceException, ValidateException {

        try {
            Album album = (Album) SqlFactory.getAlbumDao().getById(id);

            if (Validation.albumCheck(title, year, description, image)) {

                album.setTitle(title);
                album.setYear(Integer.parseInt(year));
                album.setDescription(description);
                album.setImage(image);
                album.setArtist(SqlFactory.getArtistDao().getByName(artist));

                SqlFactory.getAlbumDao().update(album);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void delete(int id) throws ServiceException {

        try {
            Album album = (Album) SqlFactory.getAlbumDao().getById(id);
            SqlFactory.getAlbumDao().delete(album);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Album> getAll() throws ServiceException {

        List<Album> albums = null;

        try {
            albums = SqlFactory.getAlbumDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return albums;
    }

}
