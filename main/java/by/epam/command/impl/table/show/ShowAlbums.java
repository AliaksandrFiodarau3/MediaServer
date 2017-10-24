package by.epam.command.impl.table.show;

import by.epam.bean.Album;
import by.epam.bean.Artist;
import by.epam.command.Command;
import by.epam.constant.Error;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowAlbums implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAlbums.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String artistTitle = request.getParameter(Parameter.PARAMETER_ALBUM_ARTIST);

        List<Album> albums = null;
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            Artist artist = ServiceFactory.getArtistService().getByName(artistTitle);

            albums = ServiceFactory.getAlbumService().getByArtist(artistTitle);

            out.print("{ \"artist\": " + mapper.writeValueAsString(artist) + " ,\"albums\": " + mapper.writeValueAsString(albums) + "}");
            out.flush();

        } catch (ServiceException e) {
            LOGGER.error(Error.ALBUMS_ALL);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
