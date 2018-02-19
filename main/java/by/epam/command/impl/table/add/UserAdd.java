package by.epam.command.impl.table.add;

import by.epam.bean.User;
import by.epam.command.Command;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for add new user {@link User}
 */

public class UserAdd implements Command {

    private static final Logger LOGGER = Logger.getLogger(UserAdd.class);

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
