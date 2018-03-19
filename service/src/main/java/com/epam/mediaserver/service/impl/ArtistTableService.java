package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.SqlArtistDao;
import com.epam.mediaserver.dao.impl.SqlGenreDao;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ArtistService;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ArtistTableService implements ArtistService{

    @Autowired
    private SqlGenreDao genreDao;

    @Autowired
    private SqlArtistDao artistDao;

    private static final Logger LOGGER = LogManager.getLogger(ArtistTableService.class);

    public Artist getByName(String title) throws ServiceException {

        Artist artist = null;

        try {
            artist = artistDao.getByName(title);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return artist;
    }

    public List<Artist> getByGenre(Long genreId) throws ServiceException {

        List<Artist> artists = null;

        try {
            artists = artistDao.getByGenre(genreId);
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
                artist.setGenre(genreDao.getByName(genre));
                artist.setTitle(title);
                artist.setDescription(description);
                artist.setImage(image);
                artistDao.add(artist);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void edit(Long id, String title, String description, String image)
        throws ServiceException, ValidateException {

        try {
            Artist artist = artistDao.getById(id);

            if (Validation.artistCheck(title, description, image)) {

                artist.setTitle(title);
                artist.setDescription(description);
                artist.setImage(image);
                artistDao.update(artist);

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
            Artist artist = artistDao.getById(id);
            artistDao.delete(artist);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }


    public List<Artist> getAll() throws ServiceException {

        List<Artist> artists = null;

        try {
            artists = artistDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return artists;
    }
}
