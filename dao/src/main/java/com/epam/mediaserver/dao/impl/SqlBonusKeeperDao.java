package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.BonusKeeperDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.entity.BonusKeeper;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * #BonusKeeperDao interface implementation for the MySQL db {@link BonusKeeperDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SqlBonusKeeperDao extends AbstractModelDao<BonusKeeper> implements BonusKeeperDao {

    @Autowired
    private SqlBonusDao bonusDao;

    @Autowired
    private SqlUserDao userDao;


    private static final Logger LOGGER = LogManager.getLogger(SqlBonusKeeperDao.class);

    private static final String CREATE_QUERY = "INSERT INTO t_bonus_keeper VALUES (null, ?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_bonus_keeper";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_bonus_keeper WHERE bonus_keeper_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_bonus_keeper SET bonus_id = ? " +
                                               "WHERE user_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_bonus_keeper WHERE user_id = ?;";
    private static final String SELECT_QUERY_BY_USER = "SELECT * FROM t_bonus_keeper WHERE user_id = ?;";

    private static final String USER_ID = "user_id";
    private static final String BONUS_ID = "bonus_id";

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getSelectQueryWithID() {
        return SELECT_QUERY_WITH_ID;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, BonusKeeper model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        BonusKeeper bonusKeeper = (BonusKeeper) model;
        ps.setInt(1, bonusKeeper.getBonus().getId());
        ps.setInt(2, bonusKeeper.getUser().getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {

        return preparedStatementForCreate(con, (BonusKeeper) model, query);
    }


    @Override
    protected int preparedStatementForDelete(Connection con, BonusKeeper model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);
        BonusKeeper bonusKeeper = (BonusKeeper) model;

        ps.setInt(1, bonusKeeper.getUser().getId());

        return ps.executeUpdate();
    }


    @Override
    protected BonusKeeper parseResult(ResultSet rs) throws DAOException {
        BonusKeeper bonusKeeper = new BonusKeeper();

        try {
            User user = (User) userDao.getById(    rs.getLong(USER_ID));
            Bonus bonus = (Bonus) bonusDao.getById(rs.getLong(BONUS_ID));
            bonusKeeper.setBonus(bonus);
            bonusKeeper.setUser(user);

            return bonusKeeper;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }


    }

    @Override
    public List<BonusKeeper> getAllByUser(int id) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY_BY_USER);) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<BonusKeeper> list = new ArrayList<>();

            while (rs.next()) {
                BonusKeeper bonusKeeper = (BonusKeeper) parseResult(rs);
                list.add(bonusKeeper);
            }

            return list;

        } catch (ConnectionPoolException e) {
            LOGGER.error( "Connection is not open", e);
            throw new DAOException( "Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        }
    }
}
