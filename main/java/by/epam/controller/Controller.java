package by.epam.controller;

import by.epam.command.Command;
import by.epam.command.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Called by the server to handle client POST request type.
     * Use special set commands for work
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command  = CommandManager.getInstance().getCommand(commandName);
        command.execute(request, response);
    }


}
