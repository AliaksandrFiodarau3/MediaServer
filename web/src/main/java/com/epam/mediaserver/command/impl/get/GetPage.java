package com.epam.mediaserver.command.impl.get;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.ServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            
            request.getSession().setAttribute(Attribute.ATTRIBUTE_PAGE, ServiceFactory.getUserService().getPage());

        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
