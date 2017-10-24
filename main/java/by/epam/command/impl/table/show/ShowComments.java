package by.epam.command.impl.table.show;

import by.epam.bean.Comment;
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

public class ShowComments implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowComments.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();

        String id = request.getParameter(Parameter.PARMETER_SONG_ID);

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {

            Song song = ServiceFactory.getSongService().getById(Integer.parseInt(id));

            List<Comment>  comments =  ServiceFactory.getCommentService().getBySong(id);

            out.print("{\"song\": " + mapper.writeValueAsString(song) + ",\"comments\": " + mapper.writeValueAsString(comments) + "}");

        } catch (ServiceException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            out.print(Messanger.sendMessage(Message.DAO_ERROR));
        }
    }
}
