package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String idSong = request.getParameter(Parameter.PARMETER_SONG_ID);
        String text = request.getParameter(Parameter.PARMETER_MESSAGE_TEXT);
        String userLogin = (String) request.getSession().getAttribute(Attribute.ATTRIBUTE_USER_LOGIN);

        try {

            ServiceFactory.getCommentService().add(Integer.parseInt(idSong), userLogin, text);

        } catch (ServiceException e) {
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
