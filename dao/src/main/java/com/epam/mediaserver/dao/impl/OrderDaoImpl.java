package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.OrderDao;
import com.epam.mediaserver.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class OrderDaoImpl extends AbstractModelDao<Order, Long> implements OrderDao {

    @Autowired
    private UserDaoImpl userDao;

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    private static final String
        CREATE_QUERY =
        "INSERT INTO t_order(user_id, order_price, order_time, order_date, order_number) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_order";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_order WHERE order_id = ?";
    private static final String SELECT_QUERY_BY_USER = "SELECT * FROM t_order WHERE user_id = ?";
    private static final String SELECT_QUERY_BY_NUMBER = "SELECT * FROM t_order WHERE order_number = ?";
    private static final String
        UPDATE_QUERY =
        "UPDATE t_order SET user_id = ?, order_price = ?, order_time = ?, order_date = ?, order_number WHERE order_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_order WHERE order_id = ?;";

    private static final String USER_ID = "user_id";
    private static final String ORDER_NUMBER = "order_number";
    private static final String ORDER_PRICE = "order_price";
    private static final String ORDER_ID = "order_id";
    private static final String ORDER_TIME = "order_time";
    private static final String ORDER_DATE = "order_date";

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Long getKey(Order entity) {
        return null;
    }
}
