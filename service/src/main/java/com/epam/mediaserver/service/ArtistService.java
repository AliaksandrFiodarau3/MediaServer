package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exeption.DAOException;

import java.util.List;

public interface ArtistService extends CrudService<Artist,Long>{

    Artist getByName(String title) throws ServiceException, DAOException;

    List<Artist> getByGenre(Long genreId) throws ServiceException ;

}
