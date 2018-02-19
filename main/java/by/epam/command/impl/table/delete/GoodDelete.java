package by.epam.command.impl.table.delete;

import by.epam.command.Command;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
import by.epam.service.ServiceFactory;
import by.epam.service.impl.GoodTableService;
import by.epam.util.Messanger;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoodDelete implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoodTableService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_ORDER_SONG_ID);

        try {

            ServiceFactory.getGoodTableService().delete(Integer.parseInt(id));

        }  catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
