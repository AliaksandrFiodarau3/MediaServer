package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AlbumDao;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.Query;

/**
 * #AlbumDao interface implementation for the MySQL db {@link Album}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class AlbumDaoImpl extends AbstractModelDao<Album, Long> implements AlbumDao {

      private static final Logger LOGGER = LogManager.getLogger(AlbumDaoImpl.class);


    private static final String SELECT_QUERY_WITH_ID = "select * from t_album where album_id = ?;";

    private static final String BY_ARTIST_QUERY = "SELECT * FROM t_album " +
                                                  "WHERE artist_id = ?";
    private static final String BY_NAME_QUERY = "SELECT * FROM t_album WHERE album_title = ?;";


    private static final String ALBUM_ID = "album_id";
    private static final String ARTIST_ID = "artist_id";
    private static final String ALBUM_YEAR = "album_year";
    private static final String ALBUM_TITLE = "album_title";
    private static final String ALBUM_DESCRIPTION = "album_description";
    private static final String ALBUM_IMAGE = "album_image";

    public AlbumDaoImpl() {
        super(Album.class);
    }

    @Override
    public List<Album> getByArtist(Long id) throws DAOException {
        Query query = getEntityManager().createQuery("from Album a WHERE a.artist.id = ?1");

        query.setParameter(1, id);

        List<Album> albums =  query.getResultList();

        return albums;
    }

    @Override
    public Album getByName(String title) throws DAOException {
        return null;
    }

    @Override
    public Long getKey(Album entity) {
        return null;
    }
}
