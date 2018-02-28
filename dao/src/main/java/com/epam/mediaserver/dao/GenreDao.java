package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.exeption.DAOException;

public interface GenreDao {


    /**
     * Method return entity of genre {@link Genre} by title genre
     *
     * @param title for search a field in the database by user login
     * @return entity of user {@link Genre}
     * @throws DAOException if database error was detected
     */

    Genre getByName(String title) throws DAOException;

}
