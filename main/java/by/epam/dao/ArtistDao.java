package by.epam.dao;

import by.epam.bean.Artist;
import by.epam.exeption.dao.DAOException;
/**
 * Interface for the Data Access Object that the Artist entity uses
 * {@link Artist}
 */

public interface ArtistDao {

    /**
     * Method return a String with query
     *
     * @return  String
     */

    String getByNameQuery();

    /**
     * Method receives a entity of artist {@link Artist}
     *
     * @param title
     *            for search a field in the database by name
     * @return entity of artist {@link Artist}
     * @throws DAOException
     *             if database error was detected
     */

    Artist getByName(String title) throws DAOException;
}
