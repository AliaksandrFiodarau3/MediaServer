package com.epam.mediaserver.command.impl.check;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLogin implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CheckLogin.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            if (ServiceFactory.getUserService().checkLogin(request.getParameter(Parameter.PARMETER_CHECK_FIELD))) {
                Messanger.sendMessage(response, Message.LOGIN_ALREADY);
            }
        } catch (ServiceException e) {
            LOGGER.info(Error.LOGIN_EXISTS);
        }

    }
}
