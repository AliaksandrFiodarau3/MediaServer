package com.epam.mediaserver.command.impl.add;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Optional;
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
        try {

        String artist = Optional.ofNullable(request.getParameter(Parameter.PARAMETER_ALBUM_ARTIST))
            .orElseThrow(()-> new ValidateException(VALIDATION_ERROR));

        String title = Optional.ofNullable(request.getParameter(Parameter.PARAMETER_ALBUM_TITLE))
            .orElseThrow(()-> new ValidateException(VALIDATION_ERROR));

        String year = Optional.ofNullable(request.getParameter(Parameter.PARAMETER_ALBUM_YEAR))
            .orElseThrow(()-> new ValidateException(VALIDATION_ERROR));

        String description = Optional.ofNullable(request.getParameter(Parameter.PARAMETER_ALBUM_DESCRIPTION))
            .orElseThrow(()-> new ValidateException(VALIDATION_ERROR));

        String image = Optional.ofNullable(request.getParameter(Parameter.PARAMETER_ALBUM_IMAGE))
            .orElseThrow(()-> new ValidateException(VALIDATION_ERROR));

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
