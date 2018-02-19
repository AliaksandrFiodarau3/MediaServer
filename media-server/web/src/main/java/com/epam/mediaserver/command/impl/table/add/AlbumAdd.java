package com.epam.mediaserver.command.impl.table.add;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Album;
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
 * Command for add new album {@link Album}
 */

public class AlbumAdd implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AlbumAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String artist = request.getParameter(Parameter.PARAMETER_ALBUM_ARTIST);
        String title = request.getParameter(Parameter.PARAMETER_ALBUM_TITLE);
        String year = request.getParameter(Parameter.PARAMETER_ALBUM_YEAR);
        String description = request.getParameter(Parameter.PARAMETER_ALBUM_DESCRIPTION);
        String image = request.getParameter(Parameter.PARAMETER_ALBUM_IMAGE);

        try {

            ServiceFactory.getAlbumService().add(artist, title, year, description, image);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}

