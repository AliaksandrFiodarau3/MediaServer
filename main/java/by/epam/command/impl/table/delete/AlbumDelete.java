package by.epam.command.impl.table.delete;

import by.epam.bean.Album;
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
 * Command for delete album {@link Album}
 */

public class AlbumDelete implements Command {

    private static final Logger LOGGER = Logger.getLogger(AlbumDelete.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(Parameter.PARAMETER_ALBUM_ID));

        try {
            ServiceFactory.getAlbumService().delete(id);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
