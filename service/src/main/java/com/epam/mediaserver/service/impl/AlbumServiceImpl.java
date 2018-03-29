package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.AlbumDao;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.AlbumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlbumServiceImpl extends CrudServiceImpl<Album, Long> implements AlbumService {

    private static final Logger LOGGER = LogManager.getLogger(AlbumServiceImpl.class);

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao) {
        super(albumDao);
    }

    public Album getByName(String title) throws DAOException {

        return ((AlbumDao) getDao()).getByName(title);
    }

    public List<Album> getByArtist(Long artistId) throws ServiceException {

        List<Album> albums = null;

        try {
            albums =((AlbumDao) getDao()).getByArtist(artistId);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return albums;
    }
}
