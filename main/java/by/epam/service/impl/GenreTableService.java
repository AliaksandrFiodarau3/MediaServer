package by.epam.service.impl;

import by.epam.bean.Genre;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.util.List;

public class GenreTableService {

    private static final Logger LOGGER = Logger.getLogger(GenreTableService.class);

    private static final String DEFAULT_IMAGE = "http://metalgrounds.ru/images/empty-album-cover.png";


    public Genre getByName(String title) throws ServiceException {

        Genre genre = null;

        try {
            genre = SqlFactory.getGenreDao().getByName(title);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return genre;
    }

    public void add( String title,  String description, String image) throws ValidateException, ServiceException {

        try {
            if (Validation.genreCheck(title, description)) {

                Genre genre = new Genre();

                genre.setTitle(title);
                genre.setDescription(description);

                if(image == null){
                    genre.setImage(DEFAULT_IMAGE);

                }else {
                    genre.setImage(image);
                }

                SqlFactory.getGenreDao().add(genre);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public void edit(int id, String title, String description, String image) throws ServiceException, ValidateException {

        try {
            Genre genre = (Genre) SqlFactory.getGenreDao().getById(id);

            if (Validation.genreCheck(title, description)) {

                genre.setTitle(title);
                genre.setDescription(description);
                if(image != null){
                    genre.setImage(image);
                }

                SqlFactory.getGenreDao().update(genre);

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
            Genre genre = (Genre) SqlFactory.getGenreDao().getById(id);
            SqlFactory.getGenreDao().delete(genre);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Genre> getAll() throws ServiceException {

        List<Genre> genres = null;

        try {
            genres = SqlFactory.getGenreDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return genres;
    }

}
