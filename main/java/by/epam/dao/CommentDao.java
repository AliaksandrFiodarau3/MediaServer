package by.epam.dao;

import by.epam.bean.Comment;
import by.epam.exeption.dao.DAOException;

import java.util.List;

public interface CommentDao {

    /**
     * Method return a String with query
     *
     * @return  String
     */

    String getByCommentQuery();

    /**
     * Method receives title of song and find equal fields in table t_comment
     *
     * @param song
     *
     * @return entity of song
     * @throws  DAOException
     *             if database error was detected
     */

    List<Comment> getBySong(String song) throws  DAOException;
}
