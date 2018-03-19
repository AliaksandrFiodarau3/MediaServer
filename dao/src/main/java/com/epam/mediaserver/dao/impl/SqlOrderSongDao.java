package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.OrderSongDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.entity.Song;
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
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SqlOrderSongDao extends AbstractModelDao<OrderSong> implements OrderSongDao {

    @Autowired
    private SqlSongDao songDao;

    @Autowired
    private SqlOrderDao orderDao;

    private static final Logger LOGGER = LogManager.getLogger(SqlOrderSongDao.class);

    private static final String CREATE_QUERY = " INSERT INTO t_order_song (order_id, song_id) VALUES (?, ?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_order_song";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_order_song WHERE order_song_id = ?";
    private static final String SELECT_QUERY_BY_ORDER = "SELECT * FROM t_order_song WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_order_song SET song_id = ?, order_id = ? " +
                                               "WHERE order_song_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_order_song WHERE order_song_id = ?;";

    private static final String ORDER_SONG_ID = "order_song_id";
    private static final String SONG_ID = "song_id";
    private static final String ORDER_ID = "order_id";

    @Override
    protected String getCreateQuery() {
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
    protected int preparedStatementForCreate(Connection con, OrderSong model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        OrderSong orderSong = (OrderSong) model;
        ps.setLong(1, orderSong.getOrder().getId());
        ps.setLong(2, orderSong.getSong().getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        OrderSong orderSong = (OrderSong) model;
        ps.setLong(1, orderSong.getSong().getId());
        ps.setLong(2, orderSong.getOrder().getId());
        ps.setLong(3, orderSong.getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, OrderSong model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);
        OrderSong orderSong = (OrderSong) model;

        ps.setLong(1, orderSong.getId());

        return ps.executeUpdate();
    }

    public List<OrderSong> getByOrder(int orderId) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY_BY_ORDER);) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            List<OrderSong> list = new ArrayList<>();

            while (rs.next()) {
                OrderSong goods = (OrderSong) parseResult(rs);
                list.add(goods);
            }

            return list;

        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        }
    }

    @Override
    protected OrderSong parseResult(ResultSet rs) throws DAOException {
        OrderSong orderSong = new OrderSong();

        try {
            orderSong.setId(rs.getLong(ORDER_SONG_ID));
            orderSong.setSong( songDao.getById(   rs.getLong(SONG_ID)));
            orderSong.setOrder(orderDao.getById(rs.getLong(ORDER_ID)));


        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }

        return orderSong;
    }
}
