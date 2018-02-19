package com.epam.mediaserver.command.impl.table.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.service.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowGoods implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowGoods.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //String id = request.getParameter(Parameter.PARMETER_ORDER_ID);

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        /*  try {*/
        request.getSession().setAttribute("order", ServiceFactory.getOrderUserService().getOrder());
        request.getSession().setAttribute("orderSongs", ServiceFactory.getOrderUserService().getAllGoodsInOrder());
        Order order = (Order) request.getSession().getAttribute("order");
        //Order order = ServiceFactory.getOrderTableService().getById(Integer.parseInt(id));

        List<OrderSong> goods = (List<OrderSong>) request.getSession().getAttribute("orderSong");
        // List<OrderSong> goods = ServiceFactory.getGoodTableService().getByOrder(order.getId());

        out.print("{\"goods\": " + mapper.writeValueAsString(goods) + "}");

      /*  } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }*/
    }
}
