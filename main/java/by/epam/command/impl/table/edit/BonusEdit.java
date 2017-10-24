package by.epam.command.impl.table.edit;

import by.epam.bean.Bonus;
import by.epam.command.Command;
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
 * Command for update bonus {@link Bonus}
 * */

public class BonusEdit implements Command {

    private static final Logger LOGGER = Logger.getLogger(BonusEdit.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_BONUS_ID);
        String title = request.getParameter(Parameter.PARMETER_BONUS_TITLE);
        String description = request.getParameter(Parameter.PARMETER_BONUS_DESCRIPTION);
        String code = request.getParameter(Parameter.PARMETER_BONUS_CODE);
        String discount = request.getParameter(Parameter.PARMETER_BONUS_DISCOUNT);

        try {
            ServiceFactory.getBonusService().edit(Integer.parseInt(id),title,description,discount, code);

        } catch (ValidateException e) {
            LOGGER.info(VALIDATION_ERROR);
            Messanger.sendMessage(response, Message.VALIDATION_ERROR);
        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}

