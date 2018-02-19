package com.epam.mediaserver.command.impl;

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

public class CheckEmail implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CheckEmail.class);

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
