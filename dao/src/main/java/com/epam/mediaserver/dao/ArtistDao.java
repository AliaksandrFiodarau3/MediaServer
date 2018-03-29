package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exeption.DAOException;

/**
 * Interface for the Data Access Object that the Artist entity uses
 * {@link Artist}
 */


public interface ArtistDao extends CrudDao<Artist,Long>{



    /**
     * Method receives a entity of artist {@link Artist}
     *
     * @param title for search a field in the database by name
     * @return entity of artist {@link Artist}
     * @throws DAOException if database error was detected
     */

    Artist getByName(String title) throws DAOException;
}
