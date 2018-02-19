package com.epam.mediaserver.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for Command pattern realization
 */

public interface Command {

    String VALIDATION_ERROR = "Validation is error.";
    String DB_ERROR = "Error in data base.";
    String BUY_ERROR = "Error in the purchase of goods.";
    /**
     * @param request  HttpServletRequest
     *                 request from application's client
     * @param response HttpServletResponse
     *                 response which will be send to the client
     */

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
