package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.SongDao;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.SongService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl extends CrudServiceImpl<Song,Long> implements SongService {

    private static final Logger LOGGER = LogManager.getLogger(SongServiceImpl.class);

    SongServiceImpl(SongDao dao) {
        super(dao);
    }

    public Song getById(Long id) {

        return getDao().find(id);
    }

    public List<Song> getByAlbum(Long albumId) throws ServiceException {

        try {
            return ((SongDao)getDao()).getByAlbum(albumId);

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }
}
