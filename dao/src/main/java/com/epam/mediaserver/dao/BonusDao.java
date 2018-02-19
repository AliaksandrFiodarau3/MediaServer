package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exeption.DAOException;

/**
 * Interface for the Data Access Object that the Bonus entity uses
 * {@link Bonus}
 */

public interface BonusDao {

    /**
     * Method receives a String with select query by name
     *
     * @return String
     */

    String getSelectQueryByCode();

    /**
     * Method receives a field with a title of bonus
     *
     * @return entity of bonus
     * @throws DAOException if database error was detected
     */

    Bonus getByCode(String code) throws DAOException;

}
