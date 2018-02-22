package com.epam.mediaserver.command.impl;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Path;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.CookieAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for log off user in system
 */

public class SignOut implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        CookieAction.deleteCookies(request, response);

        ServiceFactory.getOrderUserService().clearOrder();

        session.invalidate();

        request.getRequestDispatcher(Path.PATH_TO_HOME_PAGE).forward(request, response);
    }
}
