package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;

/**
 * Interface for the Data Access Object that the Album entity uses
 * {@link Album}
 */


public interface AlbumDao {


    /**
     * Method receives a entity of album {@link Album}
     *
     * @param artistId for search a field in the database by id of artist
     * @return {@link List} instances of {@link Album}
     * @throws DAOException if database error was detected
     */

    List<Album> getByArtist(Long artistId) throws DAOException;

    /**
     * Method receives a entity of album {@link Album}
     *
     * @param title for search a field in the database by title
     * @return entity of album {@link Album}
     * @throws DAOException if database error was detected
     */

    Album getByName(String title) throws DAOException;
}
