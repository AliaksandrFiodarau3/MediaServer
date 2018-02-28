package com.epam.mediaserver.dao;

import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.exeption.PersistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for CRUD model DAO
 */

public abstract class AbstractModelDao<T extends Model> {

    protected static final String OPEN_CONNECTION_EXEPTION = "Connection is not open";
    protected static final String CLOSE_CONNECTION_EXEPTION = "Connection not closed";
    protected static final String PERSIST_EXEPTION = "Persist Exception";
    protected static final String SQL_EXEPTION = "SQL Exception";
    private static final Logger LOGGER = LogManager.getLogger();


    protected AbstractModelDao() {
    }

    /**
     * Calls query for create field
     *
     * @return String query
     */

    protected abstract String getCreateQuery();

    /**
     * Calls query for select all field
     *
     * @return String query
     */

    protected abstract String getSelectQuery();

    /**
     * Calls query for select field by id
     *
     * @return String query
     */

    protected abstract String getSelectQueryWithID();

    /**
     * Calls query for update field
     *
     * @return String query
     */

    protected abstract String getUpdateQuery();

    /**
     * Calls query for delete field
     *
     * @return String query
     */

    protected abstract String getDeleteQuery();

    /**
     * Calls query for create field
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */

    protected abstract int preparedStatementForCreate(Connection con, T model, String query) throws SQLException;

    /**
     * Calls query for update field
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */

    protected abstract int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException;

    /**
     * Calls query for delete field
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */

    protected abstract int preparedStatementForDelete(Connection con, T model, String query) throws SQLException;

    /**
     * Calls query for delete field
     *
     * @return object inherited from the interface Model
     */

    protected abstract T parseResult(ResultSet rs)
        throws SQLException, PersistException, ConnectionPoolException, DAOException;

    /**
     * Create new field in table
     *
     * @param model Object inherited from the interface Model
     */

    public void add(T model) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();
            int counter = 0;
            counter = preparedStatementForCreate(con, model, getCreateQuery());
            if (counter != 1) {
                LOGGER.error(PERSIST_EXEPTION);
                throw new DAOException(PERSIST_EXEPTION);
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
    }

    /**
     * Find field in table by ID
     *
     * @param id unique ID in the table
     */

    public T getById(int id) throws DAOException {


        T model = null;

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(getSelectQueryWithID())) {

                ps.setInt(1, id);
                try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    model = parseResult(rs);
                }
                }
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        } catch (PersistException e) {
            LOGGER.error(PERSIST_EXEPTION);
            throw new DAOException(PERSIST_EXEPTION);
        }
        return model;
    }

    /**
     * Update field in table
     *
     * @param model Object inherited from the interface Model
     */

    public void update(T model) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();

            int counter = preparedStatementForUpdate(con, model, getUpdateQuery());

            if (counter != 1) {
                LOGGER.error(PERSIST_EXEPTION);
                throw new DAOException(PERSIST_EXEPTION);
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
    }

    /**
     * Delete field in table
     *
     * @param model Object inherited from the interface Model
     */

    public void delete(T model) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.takeConnection();
            int counter = preparedStatementForDelete(con, model, getDeleteQuery());

            if (counter != 1) {
                LOGGER.error(PERSIST_EXEPTION);
                throw new DAOException(PERSIST_EXEPTION);
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
    }

    /**
     * Delete field in table
     *
     * @return collection List with objects inherited from the interface Model
     */

    public List<T> getAll() throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<T> list = new ArrayList<T>();

        try {
            con = ConnectionPool.takeConnection();

            ps = con.prepareStatement(getSelectQuery());
            rs = ps.executeQuery();

            while (rs.next()) {
                T model = parseResult(rs);
                list.add(model);
            }

        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION, e);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION, e);
            throw new DAOException(SQL_EXEPTION);
        } catch (PersistException e) {
            LOGGER.error(PERSIST_EXEPTION, e);
            throw new DAOException(PERSIST_EXEPTION);
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
