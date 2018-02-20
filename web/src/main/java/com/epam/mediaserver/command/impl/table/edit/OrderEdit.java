package com.epam.mediaserver.command.impl.table.edit;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Order;
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
 * Command for update order {@link Order}
 */

public class OrderEdit implements Command {

    private static final Logger LOGGER = LogManager.getLogger(OrderEdit.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_ORDER_ID);
        int number = Integer.parseInt(request.getParameter(Parameter.PARMETER_ORDER_NUMBER));
        String price = request.getParameter(Parameter.PARMETER_ORDER_PRICE);
        String user = request.getParameter(Parameter.PARMETER_ORDER_USER);

        try {

            ServiceFactory.getOrderTableService().edit(Integer.parseInt(id), Double.parseDouble(price), user, number);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
