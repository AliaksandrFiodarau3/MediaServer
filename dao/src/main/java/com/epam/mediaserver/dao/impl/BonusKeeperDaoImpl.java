package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.BonusKeeperDao;
import com.epam.mediaserver.entity.BonusKeeper;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * #BonusKeeperDao interface implementation for the MySQL db {@link BonusKeeperDaoImpl}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class BonusKeeperDaoImpl extends AbstractModelDao<BonusKeeper, Long> implements BonusKeeperDao {

    private static final Logger LOGGER = LogManager.getLogger(BonusKeeperDaoImpl.class);

    private static final String CREATE_QUERY = "INSERT INTO t_bonus_keeper VALUES (null, ?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_bonus_keeper";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_bonus_keeper WHERE bonus_keeper_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_bonus_keeper SET bonus_id = ? " +
                                               "WHERE user_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_bonus_keeper WHERE user_id = ?;";
    private static final String SELECT_QUERY_BY_USER = "SELECT * FROM t_bonus_keeper WHERE user_id = ?;";

    private static final String USER_ID = "user_id";
    private static final String BONUS_ID = "bonus_id";

    public BonusKeeperDaoImpl() {
        super(BonusKeeper.class);
    }


    @Override
    public List<BonusKeeper> getAllByUser(int id) throws DAOException {
        return null;
    }

    @Override
    Long getKey(BonusKeeper entity) {
        return null;
    }
}
