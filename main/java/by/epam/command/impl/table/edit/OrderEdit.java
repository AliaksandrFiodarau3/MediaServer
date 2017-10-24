package by.epam.command.impl.table.edit;

import by.epam.bean.Order;
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
 * Command for update order {@link Order}
 * */

public class OrderEdit implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderEdit.class);

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
