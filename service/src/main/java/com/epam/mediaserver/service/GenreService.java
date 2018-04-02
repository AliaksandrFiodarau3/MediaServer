package com.epam.mediaserver.service;

import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.exeption.DAOException;

public interface GenreService extends CrudService<Genre,Long>{

    public Genre getByName(String title) throws DAOException;

}
