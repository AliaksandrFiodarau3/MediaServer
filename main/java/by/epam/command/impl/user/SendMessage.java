package by.epam.command.impl.user;

import by.epam.bean.Comment;
import by.epam.bean.Song;
import by.epam.bean.User;
import by.epam.command.Command;
import by.epam.constant.Attribute;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

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
