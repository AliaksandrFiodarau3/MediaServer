package by.epam.dao.impl.pool;

import by.epam.exeption.dao.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
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

    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private final static String SQL_EXCEPTION = "SQL Exeption in ConnectionPoll";
    private final static String CLASS_NOT_FOUND_EXCEPTION = "Can't find database driver class";
    private final static String CONNECTING_ERROR = "Error connecting to the data source.";
    private final static String NOT_CLOSED_RESULT_SET = "ResultSet is not closed.";
    private final static String NOT_CLOSED_PREPARE_STATEMENT = "PrepareStatement isn't close.";
    private final static String NOT_RETURN_CONNECTING_ERROR = "Connection isn't return to the pool.";
    private final static String NOT_CLOSED_CONNECTING_QUEUE = "Connection queue not closed";
    private final static String NOT_CLOSED_CONNECTION = "Attempting to close closed connection.";
    private final static String DELETING_CONNECTION_ERROR = "Error deleting connection from the given away connections pool.";
    private final static String ALLOCATING_CONNECTION_ERROR = "Error allocating connection in the pool.";


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
    private static int poolSize;

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
            this.poolSize = Integer.parseInt(DBParameter.POOL_SIZE);
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public static final ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Initialize database driver, connect to database, create connections
     * Method execute in listener
     * {@link by.epam.listener.ConnectionListener}
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

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) throws ConnectionPoolException {

        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            LOGGER.warn(NOT_CLOSED_RESULT_SET + e);
            throw new ConnectionPoolException(NOT_CLOSED_RESULT_SET);
        }

        try {
            if (statement != null) statement.close();

        } catch (SQLException e) {
            LOGGER.warn(NOT_CLOSED_PREPARE_STATEMENT);
            throw new ConnectionPoolException(NOT_CLOSED_PREPARE_STATEMENT);
        }

        try {
            if (connection != null) connection.close();
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
     * Wrapper for Connection interface
     */

    private static class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException(NOT_CLOSED_CONNECTION);
            }

            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }

            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenArrayConQueue.remove(this)) {
                throw new SQLException(DELETING_CONNECTION_ERROR);
            }

            if (!connectionQueue.offer(this)) {
                throw new SQLException(ALLOCATING_CONNECTION_ERROR);
            }
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }

}
