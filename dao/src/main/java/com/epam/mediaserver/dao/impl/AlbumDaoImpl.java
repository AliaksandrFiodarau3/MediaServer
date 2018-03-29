package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AlbumDao;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * #AlbumDao interface implementation for the MySQL db {@link Album}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class AlbumDaoImpl extends AbstractModelDao<Album, Long> implements AlbumDao {

      private static final Logger LOGGER = LogManager.getLogger(AlbumDaoImpl.class);

    private static final String CREATE_QUERY =
        "INSERT INTO t_album (artist_id, album_year, album_description, album_image ,album_title) VALUES (?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_album;";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_album where album_id = ?;";
    private static final String UPDATE_QUERY =
        "update t_album set artist_id = ?, album_year = ? ,album_description = ? , album_image = ?" +
        "where album_title = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_album WHERE album_title = ?;";

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
    public List<Album> getByArtist(Long artistId) throws DAOException {
        return null;
    }

    @Override
    public Album getByName(String title) throws DAOException {
        return null;
    }

    @Override
    Long getKey(Album entity) {
        return null;
    }
}
