package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Comment;
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

public class ShowComments implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowComments.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();

        String id = request.getParameter(Parameter.PARMETER_SONG_ID);

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {

            Song song = ServiceFactory.getSongService().getById(Long.parseLong(id));

            List<Comment> comments = ServiceFactory.getCommentService().getBySong(id);

            out.print("{\"song\": " + mapper.writeValueAsString(song) + ",\"comments\": " + mapper
                .writeValueAsString(comments) + "}");

        } catch (ServiceException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            out.print(Messanger.sendMessage(Message.DAO_ERROR));
        }
    }
}
