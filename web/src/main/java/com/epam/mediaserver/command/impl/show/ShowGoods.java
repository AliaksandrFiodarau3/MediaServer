package com.epam.mediaserver.command.impl.show;

import com.epam.mediaserver.command.Command;
import com.epam.mediaserver.entity.OrderSong;
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

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

      /*  request.getSession().setAttribute("order", ServiceFactory.getOrderUserService().getOrder());
        request.getSession().setAttribute("orderSongs", ServiceFactory.getOrderUserService().getAllGoodsInOrder());*/
       /* Order order = (Order) request.getSession().getAttribute("order");*/

        List<OrderSong> goods = (List<OrderSong>) request.getSession().getAttribute("orderSong");

        goods.sort((g1,g2) -> g1.getOrder().getNumber());

        out.print("{\"goods\": " + mapper.writeValueAsString(goods) + "}");


    }
}
