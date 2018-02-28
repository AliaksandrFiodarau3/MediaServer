package com.epam.mediaserver.dao.impl.pool;

import com.epam.mediaserver.exeption.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * ConnectionPool for SQL data base
 */

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private final static String SQL_EXCEPTION = "SQL Exeption in ConnectionPoll";
    private final static String CLASS_NOT_FOUND_EXCEPTION = "Can't find database driver class";
    private final static String CONNECTING_ERROR = "Error connecting to the data source.";
    private final static String NOT_CLOSED_RESULT_SET = "ResultSet is not closed.";
    private final static String NOT_CLOSED_PREPARE_STATEMENT = "PrepareStatement isn't close.";
    private final static String NOT_RETURN_CONNECTING_ERROR = "Connection isn't return to the pool.";
    private final static String NOT_CLOSED_CONNECTING_QUEUE = "Connection queue not closed";

    private static final int DEFAULT_POOL_SIZE = 6;

    private static ConnectionPool instance = new ConnectionPool();

    /**
     * Synchronized queue of prepared available connections
     */

    private static BlockingQueue<Connection> connectionQueue;

    /**
     * Synchronized queue of given connections
     */

    private static BlockingQueue<Connection> givenArrayConQueue;

    private static String driverName;
    private static String url;
    private static String user;
    private static String password;
    private static Integer poolSize;

    /**
     * Initialize database parameters
     */

    private ConnectionPool() {


        ResourceBundle resourceBundle = ResourceBundle.getBundle(DBParameter.DB);

        this.driverName = resourceBundle.getString(DBParameter.DRIVER);
        this.url = resourceBundle.getString(DBParameter.URL);
        this.user = resourceBundle.getString(DBParameter.USER);
        this.password = resourceBundle.getString(DBParameter.PASSWORD);

        try {
            this.poolSize = Integer.valueOf(resourceBundle.getString(DBParameter.POOL_SIZE));
            LOGGER.info("Connection pool was init.");
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public static final ConnectionPool getInstance() {
        return instance;
    }

    public static BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public static BlockingQueue<Connection> getGivenArrayConQueue() {
        return givenArrayConQueue;
    }

    private static void clearConnectionQueue() throws ConnectionPoolException {
        closeConnectionQueue(givenArrayConQueue);
        closeConnectionQueue(connectionQueue);
    }

    /**
     * Get available connection from connectionQueue
     *
     * @return DataBase connection
     * @throws ConnectionPoolException appears when can't get free connection
     */

    public static Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenArrayConQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(CONNECTING_ERROR, e);
        }
        return connection;
    }

    /**
     * Close all connections from queue
     *
     * @throws ConnectionPoolException appears when can't get free connection
     */

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet)
        throws ConnectionPoolException {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.warn(NOT_CLOSED_RESULT_SET + e);
            throw new ConnectionPoolException(NOT_CLOSED_RESULT_SET);
        }

        try {
            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            LOGGER.warn(NOT_CLOSED_PREPARE_STATEMENT);
            throw new ConnectionPoolException(NOT_CLOSED_PREPARE_STATEMENT);
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.warn(NOT_RETURN_CONNECTING_ERROR, e);
            throw new ConnectionPoolException(NOT_RETURN_CONNECTING_ERROR);
        }
    }

    /**
     * Close all connections from queue
     *
     * @throws ConnectionPoolException appears when can't get free connection
     */

    public static void closeConnectionQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
        Connection connection;
        try {
            while ((connection = queue.poll()) != null) {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();
            }
        } catch (SQLException e) {
            LOGGER.warn(NOT_CLOSED_CONNECTING_QUEUE, e);
            throw new ConnectionPoolException(NOT_CLOSED_CONNECTING_QUEUE);
        }
    }

    /**
     * Initialize database driver, connect to database, create connections
     * Method execute in listener
     * {@link com.epam.mediaserver.listener.ConnectionListener}
     *
     * @throws ConnectionPoolException appears when can't get connection or initialize database
     *                                 driver
     */

    public void initPoolDate() throws ConnectionPoolException {

        try {
            Class.forName(driverName);
            givenArrayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);

                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            LOGGER.warn(SQL_EXCEPTION);
            throw new ConnectionPoolException(SQL_EXCEPTION, e);
        } catch (ClassNotFoundException e) {
            LOGGER.warn(CLASS_NOT_FOUND_EXCEPTION);
            throw new ConnectionPoolException(CLASS_NOT_FOUND_EXCEPTION, e);
        }
    }

    /**
     * Dispose all created database connections
     */

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }
}
