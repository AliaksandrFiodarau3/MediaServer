package by.epam.command.impl.table.add;

import by.epam.bean.Album;
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
 * Command for add new album {@link Album}
 * */

public class AlbumAdd implements Command {

    private static final Logger LOGGER = Logger.getLogger(AlbumAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String artist = request.getParameter(Parameter.PARAMETER_ALBUM_ARTIST);
        String title = request.getParameter(Parameter.PARAMETER_ALBUM_TITLE);
        String year = request.getParameter(Parameter.PARAMETER_ALBUM_YEAR);
        String description = request.getParameter(Parameter.PARAMETER_ALBUM_DESCRIPTION);
        String image = request.getParameter(Parameter.PARAMETER_ALBUM_IMAGE);

        try {

            ServiceFactory.getAlbumService().add(artist,title,year,description, image);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}

