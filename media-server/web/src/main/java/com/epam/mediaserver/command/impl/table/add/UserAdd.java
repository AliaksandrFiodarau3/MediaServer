package com.epam.mediaserver.command.impl.table.add;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for add new user {@link User}
 */

public class UserAdd implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UserAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter(Parameter.PARMETER_USER_NAME);
        String surname = request.getParameter(Parameter.PARMETER_USER_SURNAME);
        String login = request.getParameter(Parameter.PARMETER_USER_LOGIN);
        String password = request.getParameter(Parameter.PARMETER_USER_PASSWORD);
        String email = request.getParameter(Parameter.PARMETER_USER_EMAIL);
        String photo = request.getParameter(Parameter.PARMETER_USER_PHOTO);

        try {
            ServiceFactory.getUserService().add(login, password, name, surname, email);
            if (photo != null) {
                ServiceFactory.getUserService().addPhoto(photo, login);
            }
        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
