package by.epam.dao.impl;

import by.epam.bean.Comment;
import by.epam.bean.Model;
import by.epam.bean.Song;
import by.epam.bean.User;
import by.epam.dao.AbstractModelDao;
import by.epam.dao.CommentDao;
import by.epam.dao.SqlFactory;
import by.epam.dao.impl.pool.ConnectionPool;
import by.epam.exeption.dao.ConnectionPoolException;
import by.epam.exeption.dao.DAOException;
import org.apache.log4j.Logger;

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

public class SqlCommentDao extends AbstractModelDao implements CommentDao {

    private static final Logger LOGGER = Logger.getLogger(SqlCommentDao.class);

    private static final String CREATE_QUERY =
            "INSERT INTO t_comment(comment_text,user_id, song_id, comment_time, comment_date) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_comment";
    private static final String SELECT_QUERY_WITH_ID = "SELECT * FROM t_comment WHERE comment_id = ?";
    private static final String UPDATE_QUERY = "UPDATE t_comment SET comment_text = ?" +
            "WHERE user_id = ? and song_id = ? and comment_time = ? and comment_date = ?;";
    private static final String DELETE_QUERY =
            "DELETE FROM t_comment " +
                    "WHERE comment_text = ? and user_id = ? and song_id = ? and  comment_time = ? and comment_date = ?;";
    private static final String BY_COMMENT_QUERY = "select * from t_comment where song_id = (select song_id from t_song where song_title = ?);";

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
    public String getByCommentQuery() {
        return BY_COMMENT_QUERY;
    }


    @Override
    protected int preparedStatementForCreate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Comment comment = (Comment) model;
        ps.setString(1, comment.getCommentText());
        ps.setInt(2, comment.getUser().getId());
        ps.setInt(3, comment.getSong().getId());
        ps.setTime(4, comment.getCommentTime());
        ps.setDate(5, comment.getCommentDate());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {

        return preparedStatementForCreate(con, model, query);
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Model model, String query) throws SQLException {

        return preparedStatementForCreate(con, model, query);
    }

    @Override
    protected Model parseResult(ResultSet rs) throws DAOException {
        Comment comment = new Comment();
        SqlFactory factory = SqlFactory.getInstance();

        User user = null;
        try {
            user = (User) factory.getUserDao().getById(rs.getInt(USER_ID));
            Song song = (Song) factory.getSongDao().getById(rs.getInt(SONG_ID));

            comment.setId(rs.getInt(COMMENT_ID));
            comment.setSong(song);
            comment.setUser(user);
            comment.setCommentText(rs.getString(COMMENT_TEXT));
            comment.setCommentTime(rs.getTime(COMMENT_TIME));
            comment.setCommentDate(rs.getDate(COMMENT_DATE));
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION);
            throw new DAOException(SQL_EXEPTION);
        }

        return comment;
    }


    @Override
    public List<Comment> getBySong(String song) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Comment> list = new ArrayList<>();
        Model comment = null;
        try {
            con = ConnectionPool.takeConnection();
            ps = con.prepareStatement(getByCommentQuery());
            ps.setString(1, song);
            rs = ps.executeQuery();
            while (rs.next()) {
                comment = parseResult(rs);
                list.add((Comment) comment);
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXEPTION);
            throw new DAOException(SQL_EXEPTION);
        } catch (ConnectionPoolException e) {
            LOGGER.error(OPEN_CONNECTION_EXEPTION);
            throw new DAOException(OPEN_CONNECTION_EXEPTION);
        } finally {
            try {
                ConnectionPool.closeConnection(con, ps, rs);
            } catch (ConnectionPoolException e) {
                LOGGER.error(CLOSE_CONNECTION_EXEPTION);
                throw new DAOException(CLOSE_CONNECTION_EXEPTION);
            }
        }

        return list;
    }
}
