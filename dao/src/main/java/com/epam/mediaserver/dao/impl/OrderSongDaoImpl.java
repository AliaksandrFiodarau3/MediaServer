/*
package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.OrderSongDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

*/
/**
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 *//*


@Repository
public class OrderSongDaoImpl extends AbstractModelDao<OrderSong, Long> implements OrderSongDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderSongDaoImpl.class);

    private static final String CREATE_QUERY = " INSERT INTO t_order_song (order_id, song_id) VALUES (?, ?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_order_song";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_order_song WHERE order_song_id = ?";
    private static final String SELECT_QUERY_BY_ORDER = "SELECT * FROM t_order_song WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_order_song SET song_id = ?, order_id = ? " +
                                               "WHERE order_song_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_order_song WHERE order_song_id = ?;";

    private static final String ORDER_SONG_ID = "order_song_id";
    private static final String SONG_ID = "song_id";
    private static final String ORDER_ID = "order_id";

    public OrderSongDaoImpl() {
        super(OrderSong.class);
    }


    @Override
    Long getKey(OrderSong entity) {
        return null;
    }

    @Override
    public List<OrderSong> getByOrder(Long orderId) {
        return null;
    }
}
*/
