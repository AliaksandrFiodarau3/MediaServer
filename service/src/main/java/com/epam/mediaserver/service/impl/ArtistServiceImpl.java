package com.epam.mediaserver.service.impl;


import com.epam.mediaserver.dao.ArtistDao;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ArtistService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArtistServiceImpl extends CrudServiceImpl<Artist, Long> implements ArtistService {

    private static final Logger LOGGER = LogManager.getLogger(ArtistServiceImpl.class);

    @Autowired
    public ArtistServiceImpl(ArtistDao artistDao) {
        super(artistDao);
    }

    public Artist getByName(String title) throws DAOException {
        return ((ArtistDao)getDao()).getByName(title);
    }

    public List<Artist> getByGenre(Long genreId) {

        return ((ArtistDao)getDao()).getByGenre(genreId);
    }

}
