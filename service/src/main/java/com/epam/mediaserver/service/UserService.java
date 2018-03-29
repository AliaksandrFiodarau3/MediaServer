package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;

public interface UserService {

    User signUp(User account);

    User signIn(String login, Long password)
        throws ServiceException;

}
