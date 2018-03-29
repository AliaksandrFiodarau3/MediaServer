package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.dao.GenreDao;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl extends CrudServiceImpl<Genre, Long> implements GenreService {

    private static final Logger LOGGER = LogManager.getLogger(GenreServiceImpl.class);

    private static final String DEFAULT_IMAGE = "http://metalgrounds.ru/images/empty-album-cover.png";

    GenreServiceImpl(GenreDao genreDao) {
        super(genreDao);
    }


    public Genre getByName(String title) throws DAOException {

        return ((GenreDao) getDao()).getByName(title);
    }

}
