package com.epam.mediaserver.command.impl.table.show;


import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
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

public class ShowAlbums implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowAlbums.class);

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

            out.print("{ \"artist\": " + mapper.writeValueAsString(artist) + " ,\"albums\": " + mapper
                .writeValueAsString(albums) + "}");
            out.flush();

        } catch (ServiceException e) {
            LOGGER.error(Error.ALBUMS_ALL);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
