package by.epam.command.impl.table.show;

import by.epam.bean.Genre;
import by.epam.command.Command;
import by.epam.constant.Error;
import by.epam.constant.Message;
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

public class ShowGenres implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowGenres.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Genre> genres = null;
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {

            genres = ServiceFactory.getGenreService().getAll();
            out.print("{\"genres\": " + mapper.writeValueAsString(genres) + "}");
            out.flush();

        } catch (ServiceException e) {
            LOGGER.error(Error.GENRES_ALL);

            Messanger.sendMessage(response,Message.DAO_ERROR);

        }
    }
}
