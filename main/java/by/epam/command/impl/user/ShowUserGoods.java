package by.epam.command.impl.user;

import by.epam.bean.Order;
import by.epam.bean.OrderSong;
import by.epam.command.Command;
import by.epam.service.ServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ShowUserGoods implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Set<OrderSong> goods = ServiceFactory.getOrderUserService().getAllGoodsInOrder();

        Order order = ServiceFactory.getOrderUserService().getOrder();

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.print("{\"goods\": " + mapper.writeValueAsString(goods) + ", \"order\": " + mapper.writeValueAsString(order) + "}");

    }
}
