package by.epam.service.impl;

import by.epam.bean.Artist;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.util.List;

public class ArtistTableService  {

    private static final Logger LOGGER = Logger.getLogger(ArtistTableService.class);

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

    public void add(String title, String genre, String description, String image) throws ValidateException, ServiceException {

        try {
            if (Validation.artistCheck(title, description,image)) {

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

    public void edit(int id, String title,  String description, String image) throws ServiceException, ValidateException {

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
