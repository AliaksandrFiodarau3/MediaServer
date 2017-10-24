package by.epam.command.impl.table.show;

import by.epam.bean.Order;
import by.epam.bean.OrderSong;
import by.epam.command.Command;
import by.epam.constant.Message;
import by.epam.constant.Parameter;
import by.epam.exeption.ServiceException;
import by.epam.service.ServiceFactory;
import by.epam.util.Messanger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class ShowGoods implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowGoods.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter(Parameter.PARMETER_ORDER_ID);

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        try {
            Order order = ServiceFactory.getOrderTableService().getById(Integer.parseInt(id));

            List<OrderSong> goods = ServiceFactory.getGoodService().getByOrder(order.getId());

            out.print("{\"goods\": " + mapper.writeValueAsString(goods) + "}");

        } catch (ServiceException e) {
            LOGGER.error(DB_ERROR);
            Messanger.sendMessage(response, Message.DAO_ERROR);
        }
    }
}
