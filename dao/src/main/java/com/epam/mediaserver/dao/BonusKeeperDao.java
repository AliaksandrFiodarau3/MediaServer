package com.epam.mediaserver.dao;

import com.epam.mediaserver.entity.BonusKeeper;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;

public interface BonusKeeperDao {



    /**
     * Method receives id of user and find equal fields in table t_bonus_keeper
     *
     * @return instance of {@link BonusKeeper}
     * @throws DAOException if database error was detected
     */

    List<BonusKeeper> getAllByUser(int id) throws DAOException;

}
