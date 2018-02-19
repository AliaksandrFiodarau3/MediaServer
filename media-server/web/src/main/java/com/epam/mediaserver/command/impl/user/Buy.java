package com.epam.mediaserver.command.impl.user;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Buy implements Command {
    private static final Logger LOGGER = LogManager.getLogger(Buy.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            ServiceFactory.getOrderUserService().saveOrder();
            ServiceFactory.getOrderUserService().clearOrder();
            request.getSession().setAttribute("order", null);
            request.getSession().setAttribute("orderSong", null);
        } catch (ServiceException e) {
            LOGGER.error(BUY_ERROR);
        }


    }
}
