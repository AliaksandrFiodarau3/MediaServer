package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;

import java.util.List;

public interface CommentService {

    void add(int songId, String userLogin, String text) throws ServiceException ;

    List<Comment> getBySong(String id) throws ServiceException ;

    List<Comment> getAll() throws ServiceException ;
}
