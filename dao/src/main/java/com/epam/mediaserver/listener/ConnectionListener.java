package com.epam.mediaserver.listener;

import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * This listener processes event crate and destroy context servlet
 */

@Component
@Primary
public class ConnectionListener extends ContextLoaderListener {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String INIT_POOL_ERROR = "Connection pool init error.";

    @Autowired
    private ConnectionPool connectionPool;

    /**
     * Gets connection pool instance
     */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

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
                LOGGER.warn(e);
            }
        }
    }

}
