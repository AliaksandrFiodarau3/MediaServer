package by.epam.command.impl.table.delete;

import by.epam.bean.Song;
import by.epam.command.Command;
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

/**
 * Command for delete song {@link Song}
 */


public class SongDelete implements Command {

    private static final Logger LOGGER = Logger.getLogger(SongDelete.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter(Parameter.PARMETER_SONG_ID));

        try {
            ServiceFactory.getSongService().delete(id);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
