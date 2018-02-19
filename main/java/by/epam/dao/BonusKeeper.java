package by.epam.dao;

import by.epam.exeption.dao.DAOException;

import java.util.List;

public interface BonusKeeper {

    /**
     * Method receives a String with select query by user login
     *
     * @return  String
     */

    String getSelectQueryByUser();

    /**
     * Method receives id of user and find equal fields in table t_bonus_keeper
     *
     * @param id
     *
     * @return instance of {@link BonusKeeper}
     * @throws  DAOException
     *             if database error was detected
     */

    List<by.epam.bean.BonusKeeper> getAllByUser(int id) throws DAOException;

}
