package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSongs implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowSongs.class);

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
            out.print(
                "{ \"album\":  " + mapper.writeValueAsString(album) + " ,\"songs\": " + mapper.writeValueAsString(songs)
                + "}");

        } catch (ServiceException e) {
            LOGGER.error(Error.GENRES_ALL);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
