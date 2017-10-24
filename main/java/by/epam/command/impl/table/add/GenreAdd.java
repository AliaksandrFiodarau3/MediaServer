package by.epam.command.impl.table.add;

import by.epam.bean.Genre;
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
 * Command for add new genre {@link Genre}
 */

public class GenreAdd implements Command {

    private static final Logger LOGGER = Logger.getLogger(GenreAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter(Parameter.PARMETER_GENRE_TITLE);
        String description = request.getParameter(Parameter.PARMETER_GENRE_DESCRIPTION);
        String image = request.getParameter(Parameter.PARMETER_GENRE_IMAGE);

        try {

            ServiceFactory.getGenreService().add(title, description, image);
        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }


    }
}
