package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CommentTableService implements CommentService{

    private static final Logger LOGGER = LogManager.getLogger(CommentTableService.class);

    public void add(Long songId, String userLogin, String text) throws ServiceException {

        try {

            Song song = (Song) SqlFactory.getSongDao().getById(songId);
            User user = SqlFactory.getUserDao().authorisation(userLogin);

            Date date = new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());

            Comment comment = new Comment(user, song, text, date, time);

            SqlFactory.getCommentDao().add(comment);

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public List<Comment> getBySong(String id) throws ServiceException {

        Song song = null;
        List<Comment> comments = null;

        try {
            song = (Song) SqlFactory.getSongDao().getById(Long.parseLong(id));

            comments = SqlFactory.getCommentDao().getBySong(song.getTitle());

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return comments;

    }

    public List<Comment> getAll() throws ServiceException {

        List<Comment> comments = null;

        try {
            comments = SqlFactory.getCommentDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return comments;
    }


}
