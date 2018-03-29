package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;


public interface SongDao extends CrudDao<Song, Long>{



    /**
     * Method receives a String of album {@link Album} and return list song {@link Song}
     *
     * @param album for search a field in the database by title album
     * @return {@link List} instances of {@link Song}
     * @throws DAOException if database error was detected
     */

    List<Song> getByAlbum(Long album) throws DAOException;

    /**
     * Method add user photo in Data Base
     *
     * @param song for search a field in the database by title song
     * @return entity of song {@link Song}
     * @throws DAOException if database error was detected
     */

    Song getByName(String song) throws DAOException;

}
