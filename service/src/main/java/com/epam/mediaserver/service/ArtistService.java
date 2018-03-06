package com.epam.mediaserver.service;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;

import java.util.List;

public interface ArtistService {

    Artist getByName(String title) throws ServiceException ;

    List<Artist> getByGenre(String genre) throws ServiceException ;

    void add(String title, String genre, String description, String image)
     throws ValidateException, ServiceException ;

    void edit(Long id, String title, String description, String image)
     throws ServiceException, ValidateException;

    void delete(Long id) throws ServiceException ;

    List<Artist> getAll() throws ServiceException ;

}
