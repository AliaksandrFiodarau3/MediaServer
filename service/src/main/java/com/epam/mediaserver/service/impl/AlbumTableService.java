package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.SqlAlbumDao;
import com.epam.mediaserver.dao.impl.SqlArtistDao;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.AlbumService;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlbumTableService implements AlbumService {

    @Autowired
    private SqlAlbumDao albumDao;

    @Autowired
    private SqlArtistDao artistDao;

    private static final Logger LOGGER = LogManager.getLogger(AlbumTableService.class);


    public Album getByName(String title) throws ServiceException {

        Album album = null;

        try {
            album = albumDao.getByName(title);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return album;
    }

    public List<Album> getByArtist(Long artistId) throws ServiceException {

        List<Album> albums = null;

        try {
            albums = albumDao.getByArtist(artistId);
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
                album.setArtist(artistDao.getByName(artist));
                album.setTitle(title);
                album.setYear(Integer.parseInt(year));
                album.setDescription(description);
                album.setImage(image);
                albumDao.add(album);

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
            Album album =  albumDao.getById(id);

            if (Validation.albumCheck(year)) {

                album.setTitle(title);
                album.setYear(Integer.parseInt(year));
                album.setDescription(description);
                album.setImage(image);
                album.setArtist(artistDao.getByName(artist));

                albumDao.update(album);

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
            Album album = (Album) albumDao.getById(id);
            albumDao.delete(album);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Album> getAll() throws ServiceException {

        List<Album> albums = null;

        try {
            albums =albumDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return albums;
    }

}
