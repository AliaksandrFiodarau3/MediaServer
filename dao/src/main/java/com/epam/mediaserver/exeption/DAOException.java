package com.epam.mediaserver.exeption;

/**
 * An exception that provides information about violation in the work of the
 * Data Access Object model
 */

public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

}
