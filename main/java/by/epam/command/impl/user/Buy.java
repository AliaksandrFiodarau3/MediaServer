package by.epam.command.impl.user;

import by.epam.command.Command;
import by.epam.exeption.ServiceException;
import by.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Buy implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            ServiceFactory.getOrderUserService().saveOrder();
            ServiceFactory.getOrderUserService().clearOrder();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
