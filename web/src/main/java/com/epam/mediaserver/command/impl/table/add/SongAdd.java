package com.epam.mediaserver.command.impl.table.add;


import static com.epam.mediaserver.constant.Message.*;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Song;
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
 * Command for add new song {@link Song}
 */

public class SongAdd implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SongAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter(Parameter.PARMETER_SONG_TITLE);
        String album = request.getParameter(Parameter.PARMETER_SONG_ALBUM);
        String duration = request.getParameter(Parameter.PARMETER_SONG_DURATION);
        String price = request.getParameter(Parameter.PARMETER_SONG_PRICE);

        try {

            ServiceFactory.getSongService().add(title, album, duration, price);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, DAO_ERROR);
        }

    }
}
