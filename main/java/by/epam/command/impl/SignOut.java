package by.epam.command.impl;

import by.epam.command.Command;
import by.epam.constant.Path;
import by.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for log off user in system
 * */

public class SignOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        session.invalidate();
        ServiceFactory.getOrderUserService().clearOrder();

        response.sendRedirect(Path.PATH_TO_INDEX_PAGE);
    }
}
