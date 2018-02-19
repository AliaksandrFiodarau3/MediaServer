package com.epam.mediaserver.command.impl.table.edit;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Genre;
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
 * Command for update genre {@link Genre}
 */

public class GenreEdit implements Command {

    private static final Logger LOGGER = LogManager.getLogger(GenreEdit.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_GENRE_ID);
        String title = request.getParameter(Parameter.PARMETER_GENRE_TITLE);
        String description = request.getParameter(Parameter.PARMETER_GENRE_DESCRIPTION);
        String image = request.getParameter(Parameter.PARMETER_GENRE_IMAGE);

        try {
            ServiceFactory.getGenreService().edit(Integer.parseInt(id), title, description, image);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
