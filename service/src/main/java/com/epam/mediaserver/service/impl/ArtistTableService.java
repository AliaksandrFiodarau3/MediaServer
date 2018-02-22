package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ArtistTableService {

    private static final Logger LOGGER = LogManager.getLogger(ArtistTableService.class);

    public Artist getByName(String title) throws ServiceException {

        Artist artist = null;

        try {
            artist = SqlFactory.getArtistDao().getByName(title);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return artist;
    }

    public List<Artist> getByGenre(String genre) throws ServiceException {

        List<Artist> artists = null;

        try {
            artists = SqlFactory.getArtistDao().getByGenre(genre);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return artists;
    }

    public void add(String title, String genre, String description, String image)
        throws ValidateException, ServiceException {

        try {
            if (Validation.artistCheck(title, description, image)) {

                Artist artist = new Artist();
                artist.setGenre(SqlFactory.getGenreDao().getByName(genre));
                artist.setTitle(title);
                artist.setDescription(description);
                artist.setImage(image);
                SqlFactory.getArtistDao().add(artist);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void edit(int id, String title, String description, String image)
        throws ServiceException, ValidateException {

        try {
            Artist artist = (Artist) SqlFactory.getArtistDao().getById(id);

            if (Validation.artistCheck(title, description, image)) {

                artist.setTitle(title);
                //artist.setGenre(SqlFactory.getGenreDao().getByName(genre));
                artist.setDescription(description);
                artist.setImage(image);
                SqlFactory.getArtistDao().update(artist);

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
            Artist artist = (Artist) SqlFactory.getArtistDao().getById(id);
            SqlFactory.getArtistDao().delete(artist);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public List<Artist> getAll() throws ServiceException {

        List<Artist> artists = null;

        try {
            artists = SqlFactory.getArtistDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return artists;
    }
}
