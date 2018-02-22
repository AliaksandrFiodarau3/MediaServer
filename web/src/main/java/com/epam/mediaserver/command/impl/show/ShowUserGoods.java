package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.entity.OrderSong;
import com.epam.mediaserver.service.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserGoods implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Set<OrderSong> goods = ServiceFactory.getOrderUserService().getAllGoodsInOrder();

        Order order = ServiceFactory.getOrderUserService().getOrder();

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.print("{\"goods\": " + mapper.writeValueAsString(goods) + ", \"order\": " + mapper.writeValueAsString(order)
                  + "}");

    }
}
