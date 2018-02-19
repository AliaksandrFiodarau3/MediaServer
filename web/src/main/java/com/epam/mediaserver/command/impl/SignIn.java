package com.epam.mediaserver.command.impl;


import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.constant.Path;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.PasswordIncorrectException;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.CookieAction;
import com.epam.mediaserver.util.Messanger;
import com.epam.mediaserver.util.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for authorization user in system
 */

public class SignIn implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User account = null;

        String login = request.getParameter(Parameter.PARMETER_USER_LOGIN);
        String password = request.getParameter(Parameter.PARMETER_USER_PASSWORD);

        try {
            account = ServiceFactory.getUserService().signIn(login, password);

            String query = QueryUtil.createHttpQueryString(request);
            request.getSession(true).setAttribute(Attribute.ATTRIBUTE_PREV_QUERY, query);

            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_ID, String.valueOf(account.getId()));
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_LOGIN, account.getLogin());
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_NAME, account.getName());
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_SURNAME, account.getSurname());
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_EMAIL, account.getEmail());
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_PHOTO, account.getPhoto());
            CookieAction.setCookie(response, Attribute.ATTRIBUTE_USER_ROLE, String.valueOf(account.isAdminRoot()));

            CookieAction.addToRequest(request);

            request.getSession().setAttribute(Attribute.ATTRIBUTE_COOKIE, request.getCookies());

            if (account.isAdminRoot()) {
                LOGGER.debug("sendRedirect(Path.PATH_TO_ADMIN_PAGE)");
                response.sendRedirect(Path.PATH_TO_ADMIN_PAGE);
            } else {
                LOGGER.debug("sendRedirect(Path.PATH_TO_USER_PAGE)");
                response.sendRedirect(Path.PATH_TO_USER_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.info(Message.AUTHORIZATION_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messanger.sendMessage(Message.AUTHORIZATION_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_HOME_PAGE).forward(request, response);
        } catch (ValidateException e) {
            LOGGER.info(Message.VALIDATION_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messanger.sendMessage(Message.VALIDATION_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_HOME_PAGE).forward(request, response);
        } catch (PasswordIncorrectException e) {
            LOGGER.info(Message.PASSWORD_INCORRECT_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE,
                                 Messanger.sendMessage(Message.PASSWORD_INCORRECT_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_HOME_PAGE).forward(request, response);
        }

    }
}
