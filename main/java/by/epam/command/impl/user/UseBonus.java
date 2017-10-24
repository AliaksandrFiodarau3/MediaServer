package by.epam.command.impl.user;

import by.epam.bean.Bonus;
import by.epam.command.Command;
import by.epam.constant.Parameter;
import by.epam.dao.SqlFactory;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UseBonus implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String bonusCode = request.getParameter(Parameter.PARMETER_BONUS_CODE);

        try {
            Bonus bonus = SqlFactory.getBonusDao().getByCode(bonusCode);

            ServiceFactory.getOrderUserService().addBonus(bonus);

        } catch (DAOException e) {
            e.printStackTrace();
        }


    }
}
