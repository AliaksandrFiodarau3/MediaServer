package by.epam.command.impl;

import by.epam.command.Command;
import by.epam.constant.Error;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckEmail implements Command {

    private static final Logger LOGGER = Logger.getLogger(CheckEmail.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            if (ServiceFactory.getUserService().checkEmail(request.getParameter(Parameter.PARMETER_CHECK_FIELD))) {
                Messanger.sendMessage(response, Message.EMAIL_ALREADY);
            }
        } catch (ServiceException e) {
            LOGGER.info(Error.LOGIN_EXISTS);
        }

    }
}
