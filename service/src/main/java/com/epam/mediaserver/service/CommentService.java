package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.exception.ServiceException;

import java.util.List;

public interface CommentService {

    void add(Long songId, String userLogin, String text) throws ServiceException ;

    List<Comment> getBySong(Long id) throws ServiceException ;

    List<Comment> getAll() throws ServiceException ;
}
