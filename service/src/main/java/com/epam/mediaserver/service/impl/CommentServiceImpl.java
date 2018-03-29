package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.CommentDao;
import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends CrudServiceImpl<Comment, Long> implements CommentService {

    private static final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        super(commentDao);
    }

    public List<Comment> getBySong(Long songId) throws ServiceException {

        try {
            return ((CommentDao)getDao()).getBySong(songId);

        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

}
