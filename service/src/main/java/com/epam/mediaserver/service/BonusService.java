package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;

import java.util.List;

public interface BonusService {

    void add(String title, String description, String discount, String code)
       throws ValidateException, ServiceException;

    void edit(int id, String title, String description, String discount, String code)
     throws ServiceException, ValidateException;

    void delete(int id) throws ServiceException;

    List<Bonus> getAll() throws ServiceException;
}
