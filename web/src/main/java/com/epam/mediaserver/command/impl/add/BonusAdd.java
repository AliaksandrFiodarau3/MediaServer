package com.epam.mediaserver.command.impl.add;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.service.ServiceFactory;
import com.epam.mediaserver.util.Messanger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for add new bonus {@link Bonus}
 */

public class BonusAdd implements Command {

    private static final Logger LOGGER = LogManager.getLogger(BonusAdd.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter(Parameter.PARMETER_BONUS_TITLE);
        String description = request.getParameter(Parameter.PARMETER_BONUS_DESCRIPTION);
        String code = request.getParameter(Parameter.PARMETER_BONUS_CODE);
        String discount = request.getParameter(Parameter.PARMETER_BONUS_DISCOUNT);

        try {

            ServiceFactory.getBonusService().add(title, description, discount, code);


        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }

    }
}
