package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.BonusKeeperDao;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.entity.BonusKeeper;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class SqlBonusKeeperDao extends AbstractModelDao implements BonusKeeperDao {

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
    public String getSelectQueryByUser() {
        return SELECT_QUERY_BY_USER;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        BonusKeeper bonusKeeper = (BonusKeeper) model;
        ps.setInt(1, bonusKeeper.getBonus().getId());
        ps.setInt(2, bonusKeeper.getUser().getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {

        return preparedStatementForCreate(con, model, query);
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);
        BonusKeeper bonusKeeper = (BonusKeeper) model;

        ps.setInt(1, bonusKeeper.getUser().getId());

        return ps.executeUpdate();
    }


    @Override
    protected Model parseResult(ResultSet rs) throws DAOException {
        BonusKeeper bonusKeeper = new BonusKeeper();
        SqlFactory factory = SqlFactory.getInstance();

        User user = null;
        try {
            user = (User) factory.getUserDao().getById(rs.getInt(USER_ID));
            Bonus bonus = (Bonus) factory.getBonusDao().getById(rs.getInt(BONUS_ID));
            bonusKeeper.setBonus(bonus);
            bonusKeeper.setUser(user);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION);
            throw new DAOException(SQL_EXEPTION);
        }

        return bonusKeeper;
    }

    @Override
    public List<BonusKeeper> getAllByUser(int id) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<BonusKeeper> list = new ArrayList<>();

        try {
            con = ConnectionPool.takeConnection();

            ps = con.prepareStatement(getSelectQueryByUser());
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                BonusKeeper bonusKeeper = (BonusKeeper) parseResult(rs);
                list.add(bonusKeeper);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
            }
        }

        return list;
    }
}
