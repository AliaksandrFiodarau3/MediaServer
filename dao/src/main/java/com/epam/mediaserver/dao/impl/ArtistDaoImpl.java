package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.ArtistDao;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.Query;

/**
 * #ArtistDao interface implementation for the MySQL db {@link Artist}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */


@Repository
public class ArtistDaoImpl extends AbstractModelDao<Artist, Long> implements ArtistDao {

    private static final Logger LOGGER = LogManager.getLogger(ArtistDaoImpl.class);


    private static final String SELECT_QUERY_WITH_ID = "select * from t_artist where artist_id = ?;";
    private static final String SELECT_QUERY_BY_GENRE = "select * from t_artist where genre_id = ?";
    private static final String BY_NAME_QUERY = "select * from t_artist where artist_title = ?;";

    private static final String ARTIST_ID = "artist_id";
    private static final String GENRE_ID = "genre_id";
    private static final String ARTIST_TITLE = "artist_title";
    private static final String ARTIST_DESCRIPTION = "artist_description";
    private static final String ARTIST_IMAGE = "artist_image";

    public ArtistDaoImpl() {
        super(Artist.class);
    }


    @Override
    public Artist getByName(String title) {
        return null;
    }

    @Override
    public List<Artist> getByGenre(Long id) {

        Query query = getEntityManager().createQuery("from Artist a WHERE a.genre.id = ?1");

        query.setParameter(1, id);

        List<Artist> artists =  query.getResultList();

        return artists;
    }

    @Override
    Long getKey(Artist entity) {
        return null;
    }
}
