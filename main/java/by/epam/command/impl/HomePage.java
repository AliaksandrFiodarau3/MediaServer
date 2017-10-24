package by.epam.command.impl;

import by.epam.command.Command;
import by.epam.constant.Attribute;
import by.epam.constant.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePage implements Command {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        String root = String.valueOf(request.getSession().getAttribute(Attribute.ATTRIBUTE_USER_ROLE));

        switch (root) {
            case "true":
                response.sendRedirect(Path.PATH_TO_ADMIN_PAGE);
                break;
            case "false":
                response.sendRedirect(Path.PATH_TO_USER_PAGE);
                break;
            default:
                response.sendRedirect(Path.PATH_TO_HOME_PAGE);
                break;
        }



    }
}
