package by.epam.dao.impl;

import by.epam.bean.Model;
import by.epam.bean.Order;
import by.epam.bean.User;
import by.epam.dao.AbstractModelDao;
import by.epam.dao.SqlFactory;
import by.epam.dao.impl.pool.ConnectionPool;
import by.epam.exeption.dao.ConnectionPoolException;
import by.epam.exeption.dao.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

public class SqlOrderDao extends AbstractModelDao {

    private static final Logger LOGGER = Logger.getLogger(SqlOrderDao.class);

    private static final String CREATE_QUERY = "INSERT INTO t_order(user_id, order_price, order_time, order_date, order_number) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_order";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_order WHERE order_id = ?";
    private static final String SELECT_QUERY_BY_USER = "SELECT * FROM t_order WHERE user_id = ?";
    private static final String SELECT_QUERY_BY_NUMBER = "SELECT * FROM t_order WHERE order_number = ?";
    private static final String UPDATE_QUERY = "UPDATE t_order SET user_id = ?, order_price = ?, order_time = ?, order_date = ?, order_number WHERE order_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_order WHERE order_id = ?;";

    private static final String USER_ID = "user_id";
    private static final String ORDER_NUMBER = "order_number";
    private static final String ORDER_PRICE = "order_price";
    private static final String ORDER_ID = "order_id";
    private static final String ORDER_TIME = "order_time";
    private static final String ORDER_DATE = "order_date";


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

    protected String getSelectQueryByUser() { return SELECT_QUERY_BY_USER;}
    protected String getSelectQueryByNumber() { return SELECT_QUERY_BY_NUMBER;}

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Order order = (Order) model;

        ps.setInt(1, order.getUser().getId());
        ps.setDouble(2, order.getPrice());
        ps.setTime(3, order.getTime());
        ps.setDate(4, order.getDate());
        ps.setInt(5, order.getNumber());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);

        Order order = (Order) model;

        ps.setInt(1, order.getUser().getId());
        ps.setDouble(2, order.getPrice());
        ps.setTime(3, order.getTime());
        ps.setDate(4, order.getDate());
        ps.setInt(5, order.getNumber());
        ps.setInt(6, order.getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {

        PreparedStatement ps = con.prepareStatement(query);

        Order order = (Order) model;

        ps.setInt(1, order.getId());

        return ps.executeUpdate();
    }

    public Order getByNumber(int number) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Order order = null;

        try {
            con = ConnectionPool.takeConnection();

            ps = con.prepareStatement(getSelectQueryByNumber());
            ps.setInt(1, number);
            rs = ps.executeQuery();

            if (rs.next()) {
                order = (Order) parseResult(rs);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        }  finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
            }
        }

        return order;
    }

    public List<Order> getByUser(int userId) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Order> list = new ArrayList<>();

        try {
            con = ConnectionPool.takeConnection();

            ps = con.prepareStatement(getSelectQueryByUser());
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = (Order) parseResult(rs);
                list.add(order);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        }  finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
            }
        }

        return list;
    }


    @Override
    protected Model parseResult(ResultSet rs) throws DAOException {
        Order order = new Order();
        SqlFactory factory = SqlFactory.getInstance();

        try {

            order.setId(rs.getInt(ORDER_ID));
            order.setNumber(rs.getInt(ORDER_NUMBER));
            User user = (User) factory.getUserDao().getById(rs.getInt(USER_ID));
            order.setUser(user);
            order.setPrice(rs.getDouble(ORDER_PRICE));
            order.setTime(rs.getTime(ORDER_TIME));
            order.setDate(rs.getDate(ORDER_DATE));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION);
            throw new DAOException(SQL_EXEPTION);
        }


        return order;
    }
}
