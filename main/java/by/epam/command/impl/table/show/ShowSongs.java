package by.epam.command.impl.table.show;

import by.epam.bean.Album;
import by.epam.bean.Song;
import by.epam.command.Command;
import by.epam.constant.Error;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
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

public class ShowSongs implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowSongs.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String albumTitle = request.getParameter(Parameter.PARMETER_SONG_ALBUM);

        List<Song> songs = null;
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        try {
            Album album = ServiceFactory.getAlbumService().getByName(albumTitle);
            songs = ServiceFactory.getSongService().getByAlbum(albumTitle);
            out.print("{ \"album\":  " + mapper.writeValueAsString(album)  + " ,\"songs\": " + mapper.writeValueAsString(songs) + "}");

        } catch (ServiceException e) {
            LOGGER.error(Error.GENRES_ALL);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
