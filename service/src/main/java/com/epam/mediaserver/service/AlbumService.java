package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;

import java.util.List;

public interface AlbumService {

    Album getByName(String title) throws ServiceException;

    List<Album> getByArtist(String artist) throws ServiceException;

    void add(String artist, String title, String year, String description, String image) throws ValidateException,
                                                                                                ServiceException;


    void edit(int id, String title, String artist, String year, String description, String image)
     throws ServiceException, ValidateException;

    void delete(int id) throws ServiceException;

    List<Album> getAll() throws ServiceException;



}
