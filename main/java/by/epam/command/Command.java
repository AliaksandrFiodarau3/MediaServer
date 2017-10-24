package by.epam.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface for Command pattern realization
 */

public interface Command {

     String VALIDATION_ERROR = "Validation is error.";
     String DB_ERROR = "Error in data base.";

    /**
     *
     * @param request HttpServletRequest
     *            request from application's client
     * @param response HttpServletResponse
     *            response which will be send to the client
     * @throws IOException
     * @throws ServletException
     */

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
