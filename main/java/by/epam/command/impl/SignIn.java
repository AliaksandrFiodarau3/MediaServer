package by.epam.command.impl;

import by.epam.bean.User;
import by.epam.command.Command;
import by.epam.constant.Attribute;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.constant.Path;
import by.epam.exeption.PasswordIncorrectException;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import by.epam.util.QueryUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for authorization user in system
 * */

public class SignIn implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User account = null;

        String login = request.getParameter(Parameter.PARMETER_USER_LOGIN);
        String password = request.getParameter(Parameter.PARMETER_USER_PASSWORD);

        try {
            account = ServiceFactory.getUserService().signIn(login, password);

            String query = QueryUtil.createHttpQueryString(request);
            request.getSession(true).setAttribute(Attribute.ATTRIBUTE_PREV_QUERY, query);
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_ID, account.getId());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_LOGIN, account.getLogin());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_NAME, account.getName());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_SURNAME, account.getSurname());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_EMAIL, account.getEmail());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_PHOTO, account.getPhoto());
            request.getSession().setAttribute(Attribute.ATTRIBUTE_USER_ROLE, account.isAdminRoot());

            response.sendRedirect(Path.PATH_TO_INDEX_PAGE);

        } catch (ServiceException e) {
            LOGGER.info(Message.AUTHORIZATION_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE,Messanger.sendMessage(Message.AUTHORIZATION_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_INDEX_PAGE).forward(request, response);
        } catch (ValidateException e) {
            LOGGER.info(Message.VALIDATION_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE,Messanger.sendMessage(Message.VALIDATION_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_INDEX_PAGE).forward(request, response);
        } catch (PasswordIncorrectException e) {
            LOGGER.info(Message.PASSWORD_INCORRECT_ERROR, e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE,Messanger.sendMessage(Message.PASSWORD_INCORRECT_ERROR));
            request.getRequestDispatcher(Path.PATH_TO_INDEX_PAGE).forward(request, response);
        }

    }
}
