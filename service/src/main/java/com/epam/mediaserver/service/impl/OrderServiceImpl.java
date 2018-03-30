package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.OrderDao;
import com.epam.mediaserver.entity.Order;
import com.epam.mediaserver.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order, Long> implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    OrderServiceImpl(OrderDao dao) {
        super(dao);
    }

}
