package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.CommentDaoImpl;
import com.epam.mediaserver.dao.impl.GenreDaoImpl;
import com.epam.mediaserver.dao.impl.SongDaoImpl;
import com.epam.mediaserver.dao.impl.UserDaoImpl;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class CommentTableService implements CommentService {

    @Autowired
    private GenreDaoImpl genreDao;

    @Autowired
    private CommentDaoImpl commentDao;

    @Autowired
    private SongDaoImpl songDao;

    @Autowired
    private UserDaoImpl userDao;

    private static final Logger LOGGER = LogManager.getLogger(CommentTableService.class);


    @Override
    public void add(Long songId, String userLogin, String text) throws ServiceException {

        try {

            Song song = songDao.getById(songId);
            User user = userDao.authorisation(userLogin);

            Date date = new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());

            Comment comment = new Comment(user, song, text, date, time);

            commentDao.add(comment);

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

    }

    public List<Comment> getBySong(Long songId) throws ServiceException {

        try {
            Song song = songDao.getById(songId);
            return commentDao.getBySong(song.getTitle());

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Comment> getAll() throws ServiceException {

        List<Comment> comments = null;

        try {
            comments = commentDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return comments;
    }


}
