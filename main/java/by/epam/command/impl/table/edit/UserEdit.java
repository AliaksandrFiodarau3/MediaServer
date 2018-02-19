package by.epam.command.impl.table.edit;

import by.epam.bean.User;
import by.epam.command.Command;
import by.epam.command.impl.table.delete.AlbumDelete;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for update user {@link User}
 * */

public class UserEdit implements Command {

    private static final Logger LOGGER = Logger.getLogger(AlbumDelete.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        int id = Integer.parseInt(request.getParameter(Parameter.PARMETER_USER_ID));
        String name = request.getParameter(Parameter.PARMETER_USER_NAME);
        String surname = request.getParameter(Parameter.PARMETER_USER_SURNAME);
        String login = request.getParameter(Parameter.PARMETER_USER_LOGIN);
        String email = request.getParameter(Parameter.PARMETER_USER_EMAIL);
        String photo = request.getParameter(Parameter.PARMETER_USER_PHOTO);

        boolean isRoot = false;
        if(request.getParameter(Parameter.PARMETER_USER_ROOT).equals("true")){
            isRoot = true;
        }

        try {

            if(photo != ""){
                ServiceFactory.getUserService().addPhoto(photo, login);
            }

            ServiceFactory.getUserService().edit(id,login,name,surname,email,isRoot);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
