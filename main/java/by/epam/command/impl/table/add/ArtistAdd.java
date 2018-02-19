package by.epam.command.impl.table.add;

import by.epam.bean.Artist;
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
 * Command for add new artist {@link Artist}
 * */

public class ArtistAdd implements Command {

    private static final Logger LOGGER = Logger.getLogger(ArtistAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter(Parameter.PARMETER_ARTIST_TITLE);
        String genre = request.getParameter(Parameter.PARMETER_ARTIST_GENRE);
        String description = request.getParameter(Parameter.PARMETER_ARTIST_DESCRIPTION);
        String image = request.getParameter(Parameter.PARMETER_ARTIST_IMAGE);

        try {

            ServiceFactory.getArtistService().add(title,genre,description,image);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
