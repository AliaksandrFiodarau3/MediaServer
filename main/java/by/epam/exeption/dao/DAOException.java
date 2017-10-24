package by.epam.exeption.dao;

/**
 * An exception that provides information about violation in the work of the
 * Data Access Object model
 */

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String message){
		super(message);
	}

	public DAOException(String message, Exception e){
		super(message, e);
	}

}
