package by.epam.service.impl;

import by.epam.bean.Comment;
import by.epam.bean.Song;
import by.epam.bean.User;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CommentTableService {

    private static final Logger LOGGER = Logger.getLogger(CommentTableService.class);

    public void add(int songId, String userLogin, String text) throws ServiceException {

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
            song = (Song) SqlFactory.getSongDao().getById(Integer.parseInt(id));

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
