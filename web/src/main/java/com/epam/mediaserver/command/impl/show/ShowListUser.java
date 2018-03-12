package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowListUser implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowListUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String page = Optional.ofNullable(request.getParameter(Parameter.PARMETER_USER_PAGE)).orElse("1");
        String value = Optional.ofNullable(request.getParameter(Parameter.PARMETER_USER_SEARCH)).orElse("");

        List<User> users;
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            users = ServiceFactory.getUserService().search(value, Integer.valueOf(page));

            List<Integer> pages = new ArrayList<>();

            for (int i = 1; i <= ServiceFactory.getUserService().getSearchPage(value) ; i++) {
                pages.add(i);
            }

            out.print("{\"users\": " + mapper.writeValueAsString(users) + ", \"value\": \""+ value +
                      "\", \"page\": " + pages.toString() + " }");

        } catch (ServiceException e) {
            LOGGER.error(Error.GENRES_ALL);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}

