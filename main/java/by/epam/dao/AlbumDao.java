package by.epam.dao;

import by.epam.bean.Album;
import by.epam.exeption.dao.DAOException;

import java.util.List;

/**
 * Interface for the Data Access Object that the Album entity uses
 * {@link Album}
 */

public interface AlbumDao {

    /**
     * Method return a String with query
     *
     * @return  String
     */

    String getByArtistQuery();

    /**
     * Method return a String with query
     *
     * @return  String
     */

    String getByNameQuery();

    /**
     * Method receives a entity of album {@link Album}
     *
     * @param artist
     *            for search a field in the database by title of artist
     * @return {@link java.util.List} instances of {@link Album}
     * @throws DAOException
     *             if database error was detected
     */

    List<Album> getByArtist(String artist) throws DAOException;

    /**
     * Method receives a entity of album {@link Album}
     *
     * @param title
     *            for search a field in the database by title
     * @return entity of album {@link Album}
     * @throws DAOException
     *             if database error was detected
     */

    Album getByName(String title) throws DAOException;
}
