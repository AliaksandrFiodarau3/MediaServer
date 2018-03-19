package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.CommentDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * #CommentDao interface implementation for the MySQL db {@link CommentDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SqlCommentDao extends AbstractModelDao<Comment> implements CommentDao {

    @Autowired
    private SqlUserDao userDao;

    @Autowired
    private SqlSongDao songDao;

    private static final Logger LOGGER = LogManager.getLogger(SqlCommentDao.class);

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

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getSelectQueryWithID() {
        return SELECT_QUERY_WITH_ID;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }



    @Override
    protected int preparedStatementForCreate(Connection con, Comment comment, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, comment.getCommentText());
        ps.setLong(2, comment.getUser().getId());
        ps.setLong(3, comment.getSong().getId());
        ps.setTime(4, comment.getCommentTime());
        ps.setDate(5, comment.getCommentDate());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {

        return preparedStatementForCreate(con, (Comment) model, query);
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Comment model, String query) throws SQLException {

        return preparedStatementForCreate(con, model, query);
    }

    @Override
    protected Comment parseResult(ResultSet rs) throws DAOException {

        Comment comment = new Comment();
        try {
            User user = userDao.getById(rs.getLong(USER_ID));
            Song song = songDao.getById(rs.getLong(SONG_ID));

            comment.setId(rs.getLong(COMMENT_ID));
            comment.setSong(song);
            comment.setUser(user);
            comment.setCommentText(rs.getString(COMMENT_TEXT));
            comment.setCommentTime(rs.getTime(COMMENT_TIME));
            comment.setCommentDate(rs.getDate(COMMENT_DATE));

            return comment;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }
    }


    @Override
    public List<Comment> getBySong(String song) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_COMMENT_QUERY);) {

            ps.setString(1, song);
            ResultSet rs = ps.executeQuery();
            List<Comment> list = new ArrayList<>();
            while (rs.next()) {
                Model comment = parseResult(rs);
                list.add((Comment) comment);
            }
            return list;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open");
            throw new DAOException("Connection is not open");
        }
    }
}
