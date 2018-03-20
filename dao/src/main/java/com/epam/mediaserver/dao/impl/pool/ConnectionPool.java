package com.epam.mediaserver.dao.impl.pool;

import com.epam.mediaserver.exeption.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ConnectionPool for SQL data base
 */

@Repository
public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final int DEFAULT_POOL_SIZE = 6;


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

    @Autowired
    public ConnectionPool() {

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
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
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
                closeConnection(connection);
            }
        } catch (SQLException e) {
            LOGGER.warn("Connection queue not closed", e);
            throw new ConnectionPoolException("Connection queue not closed");
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

    @PostConstruct
    public void initPoolDate() throws ConnectionPoolException {

        try {
            Class.forName(driverName);
            givenArrayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);

                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.warn("SQL Exeption in ConnectionPoll");
            throw new ConnectionPoolException("SQL Exeption in ConnectionPoll", e);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Can't find database driver class");
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    /**
     * Dispose all created database connections
     */

    @PreDestroy
    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private static void closeConnection(Connection connection) throws SQLException {
        if (connection.isClosed()) {
            throw new SQLException("Attempting to close closed connection.");
        }

        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }

        if (connection.isReadOnly()) {
            connection.setReadOnly(false);
        }
        if (!ConnectionPool.getGivenArrayConQueue().remove(connection)) {
            throw new SQLException("Error deleting connection from the given away connections pool.");
        }

        if (!ConnectionPool.getConnectionQueue().offer(connection)) {
            throw new SQLException("Error allocating connection in the pool.");
        }
    }
}
