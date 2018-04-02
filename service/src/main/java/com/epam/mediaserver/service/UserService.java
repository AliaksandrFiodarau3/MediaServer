package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;

public interface UserService extends CrudService<User,Long>{

    User signUp(User account) throws ServiceException;

    User signIn(String login, Long password)
        throws ServiceException;
}
