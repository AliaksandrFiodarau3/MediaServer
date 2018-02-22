package com.epam.mediaserver.command.impl;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Parameter;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ServiceFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UseBonus implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String bonusCode = request.getParameter(Parameter.PARMETER_BONUS_CODE);

        try {

            Bonus bonus = SqlFactory.getBonusDao().getByCode(bonusCode);
            ServiceFactory.getOrderUserService().addBonus(bonus);
            request.getSession().setAttribute("bonus", bonus);
            request.getSession().setAttribute("order", ServiceFactory.getOrderUserService().getOrder());
            request.getSession().setAttribute("orderSong", ServiceFactory.getOrderUserService().getAllGoodsInOrder());

        } catch (DAOException e) {
            e.printStackTrace();
        }


    }
}
