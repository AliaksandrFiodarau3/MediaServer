package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.CommentDao;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * #CommentDao interface implementation for the MySQL db {@link CommentDaoImpl}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class CommentDaoImpl extends AbstractModelDao<Comment, Long> implements CommentDao {

    private static final Logger LOGGER = LogManager.getLogger(CommentDaoImpl.class);

    private static final String CREATE_QUERY =
        "INSERT INTO t_comment(comment_text,user_id, song_id, comment_time, comment_date) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_comment";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_comment WHERE comment_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_comment SET comment_text = ?" +
                                               "WHERE user_id = ? and song_id = ? and comment_time = ? and comment_date = ?;";
    private static final String DELETE_QUERY =
        "DELETE FROM t_comment " +
        "WHERE comment_text = ? and user_id = ? and song_id = ? and  comment_time = ? and comment_date = ?;";
    private static final String BY_COMMENT_QUERY =
        "SELECT * FROM t_comment WHERE song_id = (SELECT song_id FROM t_song WHERE song_title = ?);";

    private static final String USER_ID = "user_id";
    private static final String SONG_ID = "song_id";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_TEXT = "comment_text";
    private static final String COMMENT_TIME = "comment_time";
    private static final String COMMENT_DATE = "comment_date";


    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    Long getKey(Comment entity) {
        return null;
    }

    @Override
    public List<Comment> getBySong(String song) throws DAOException {
        return null;
    }
}
