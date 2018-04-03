package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;

public interface SongService extends CrudService<Song,Long>{
    /**
     * Method receives a String of album {@link Album} and return list song {@link Song}
     *
     * @param album for search a field in the database by title album
     * @return {@link List} instances of {@link Song}
     * @throws DAOException if database error was detected
     */

    List<Song> getByAlbum(Long album) throws ServiceException;
}
