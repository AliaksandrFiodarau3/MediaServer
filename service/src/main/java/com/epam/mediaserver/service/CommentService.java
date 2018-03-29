package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Comment;
import com.epam.mediaserver.exception.ServiceException;

import java.util.List;

public interface CommentService {

    List<Comment> getBySong(Long id) throws ServiceException ;
}
