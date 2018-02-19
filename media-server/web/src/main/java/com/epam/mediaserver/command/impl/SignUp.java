package com.epam.mediaserver.command.impl;


import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.constant.Path;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import com.epam.mediaserver.util.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for registration user in system
 */

public class SignUp implements Command {

    private static Logger LOGGER = LogManager.getLogger(SignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User account = null;

        String login = request.getParameter(Parameter.PARMETER_USER_LOGIN);
        String password = request.getParameter(Parameter.PARMETER_USER_PASSWORD);
        String name = request.getParameter(Parameter.PARMETER_USER_NAME);
        String surname = request.getParameter(Parameter.PARMETER_USER_SURNAME);
        String email = request.getParameter(Parameter.PARMETER_USER_EMAIL);

        try {

            account = ServiceFactory.getUserService().signUp(login, password, name, surname, email);

        } catch (ValidateException e) {
            LOGGER.info(Message.VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.info(Message.CLIENT_SERVICE_ERROR);
            Messanger.sendMessage(response, Message.CLIENT_SERVICE_ERROR);
        }

        String query = QueryUtil.createHttpQueryString(request);
        request.getSession(true).setAttribute(Attribute.ATTRIBUTE_PREV_QUERY, query);
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_ID, account.getId());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_LOGIN, account.getLogin());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_NAME, account.getName());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_SURNAME, account.getSurname());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_EMAIL, account.getEmail());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_PHOTO, account.getPhoto());
        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_ROLE, account.isAdminRoot());

        response.sendRedirect(Path.PATH_TO_HOME_PAGE);

    }
}
