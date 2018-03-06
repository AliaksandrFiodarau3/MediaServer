package com.epam.mediaserver.command.impl.delete;

import com.epam.mediaserver.command.Command;
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

public class GoodDelete implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GoodDelete.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_ORDER_SONG_ID);

        try {

            ServiceFactory.getGoodTableService().delete(Long.parseLong(id));
            request.getSession().setAttribute("order", ServiceFactory.getOrderUserService().getOrder());
            request.getSession().setAttribute("orderSongs", ServiceFactory.getOrderUserService().getAllGoodsInOrder());


        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
