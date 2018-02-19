package by.epam.command.impl.table.add;

import by.epam.bean.Song;
import by.epam.bean.User;
import by.epam.command.Command;
import by.epam.constant.Attribute;
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

public class GoodAdd implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoodAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_SONG_ID);

        String login = (String) request.getSession().getAttribute(Attribute.ATTRIBUTE_USER_LOGIN);


        User user = null;
        try {
            user = ServiceFactory.getUserService().getByLogin(login);

            ServiceFactory.getOrderUserService().create(user);

            Song song = ServiceFactory.getSongService().getById(Integer.parseInt(id));

            ServiceFactory.getOrderUserService().addGoodToOrder(song);
            ServiceFactory.getOrderUserService().getPrice();


        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);


        }
    }
}
