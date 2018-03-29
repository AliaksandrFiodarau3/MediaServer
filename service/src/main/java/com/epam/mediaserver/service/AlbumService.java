package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;

public interface AlbumService {

    Album getByName(String title) throws ServiceException, DAOException;

    List<Album> getByArtist(Long artistId) throws ServiceException;

}
