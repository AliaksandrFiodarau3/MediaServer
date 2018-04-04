package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.BonusDao;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * #BonusDao interface implementation for the MySQL db {@link Bonus}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class BonusDaoImpl extends AbstractModelDao<Bonus, Long> implements BonusDao {

    private static final Logger LOGGER = LogManager.getLogger(BonusDaoImpl.class);

    private static final String
        CREATE_QUERY =
        "INSERT INTO t_bonus (bonus_title, bonus_description, bonus_code , bonus_discount) VALUES ( ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "Select * FROM t_bonus";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * From t_bonus WHERE bonus_id = ?";
    private static final String SELECT_QUERY_BY_CODE = "SELECT * FROM t_bonus WHERE bonus_code = ?";
    private static final String UPDATE_QUERY =
        "UPDATE t_bonus SET bonus_description = ?, bonus_discount = ?, bonus_code = ? WHERE bonus_title= ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_bonus WHERE bonus_title=?;";


    private static final String BONUS_ID = "bonus_id";
    private static final String BONUS_TITLE = "bonus_title";
    private static final String BONUS_DESCRIPTION = "bonus_description";
    private static final String BONUS_DISCOUNT = "bonus_discount";
    private static final String BONUS_CODE = "bonus_code";

    public BonusDaoImpl() {
        super(Bonus.class);
    }


    @Override
    public Bonus getByCode(String code) throws DAOException {
        return null;
    }

    @Override
    public Long getKey(Bonus entity) {
        return null;
    }
}
