package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AlbumTableService implements AlbumService{

    private static final Logger LOGGER = LogManager.getLogger(AlbumTableService.class);


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

    public void add(String artist, String title, String year, String description, String image)
        throws ServiceException, ValidateException {
        try {
            if (Validation.albumCheck(year)) {
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


    public void edit(Long id, String title, String artist, String year, String description, String image)
        throws ServiceException, ValidateException {

        try {
            Album album = (Album) SqlFactory.getAlbumDao().getById(id);

            if (Validation.albumCheck(year)) {

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

    public void delete(Long id) throws ServiceException {

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
