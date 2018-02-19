package com.epam.mediaserver.listener;

import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This listener processes event crate and destroy context servlet
 */

@WebListener
public class ConnectionListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String INIT_POOL_ERROR = "Connection pool init error.";


    private ConnectionPool connectionPool;

    /**
     * Gets connection pool instance
     */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPoolDate();
        } catch (ConnectionPoolException e) {
            LOGGER.warn(e);
            throw new RuntimeException(INIT_POOL_ERROR);
        }
    }

    /**
     * Closed all connections in connection pool
     */

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (connectionPool != null) {
            try {
                connectionPool.dispose();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

}
