package com.epam.mediaserver.controller;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.command.CommandManager;
import com.epam.mediaserver.util.CookieAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Front controller of the application
 *
 * Handles HTTP requests and HTTP response to certain instance of
 * {@link Command}
 */

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    /**
     * @see HttpServlet#HttpServlet()
     */

    public Controller() {
        super();
    }

    /**
     * Called by the server to handle client GET request type
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Called by the server to handle client POST request type.
     * Use special set commands for work
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Receiving an object reference depending on the request..
     */

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setAttribute("cookies", CookieAction.addToRequest(request));

        String commandName = request.getParameter(COMMAND);
        System.out.println(commandName);
        Command command = CommandManager.getInstance().getCommand(commandName);
        command.execute(request, response);


    }
}