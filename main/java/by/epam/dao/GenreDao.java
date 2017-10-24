package by.epam.dao;

import by.epam.bean.Genre;
import by.epam.exeption.dao.DAOException;

public interface GenreDao {

    /**
     * Method return a String with select query with title genre
     *
     * @return  String
     */

    String getSelectQueryWithName();

    /**
     * Method return entity of genre {@link Genre} by title genre
     *
     * @param title
     *            for search a field in the database by user login

     * @return entity of user {@link Genre}
     * @throws DAOException
     *             if database error was detected
     */

    Genre getByName(String title) throws DAOException;

}
