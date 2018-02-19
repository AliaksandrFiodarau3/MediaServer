package by.epam.command.impl.table.edit;

import by.epam.bean.Song;
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
 * Command for update song {@link Song}
 * */

public class SongEdit implements Command {

    private static final Logger LOGGER = Logger.getLogger(SongEdit.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_SONG_ID);
        String title = request.getParameter(Parameter.PARMETER_SONG_TITLE);
        String album = request.getParameter(Parameter.PARMETER_SONG_ALBUM);
        String duration = request.getParameter(Parameter.PARMETER_SONG_DURATION);
        String price = request.getParameter(Parameter.PARMETER_SONG_PRICE);

        try {

            ServiceFactory.getSongService().edit(Integer.parseInt(id), title, album, duration, price);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
