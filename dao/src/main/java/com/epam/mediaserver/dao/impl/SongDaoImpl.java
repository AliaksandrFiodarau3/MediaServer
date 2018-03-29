package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.SongDao;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * #SongDao interface implementation for the MySQL db {@link SongDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SongDaoImpl extends AbstractModelDao<Song, Long> implements SongDao {


    private static final Logger LOGGER = LogManager.getLogger(SongDaoImpl.class);

    private static final String
        CREATE_QUERY =
        "INSERT INTO t_song (album_id, song_duration, song_price, song_title) VALUES (?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_song";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_song where song_id = ?;";
    private static final String
        UPDATE_QUERY =
        "update t_song set album_id = ?, song_duration = ? ,song_price = ?, song_title = ? where song_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_song WHERE song_id = ?;";
    private static final String BY_ALBUM_QUERY = "SELECT * FROM t_song WHERE album_id = ?";
    private static final String BY_NAME_QUERY = "SELECT * FROM t_song WHERE song_title = ?;";

    private static final String SONG_ID = "song_id";
    private static final String ALBUM_ID = "album_id";
    private static final String SONG_TITLE = "song_title";
    private static final String SONG_DURATION = "song_duration";
    private static final String SONG_PRICE = "song_price";

    public SongDaoImpl() {
        super(Song.class);
    }

    @Override
    public List<Song> getByAlbum(Long album) throws DAOException {
        return null;
    }

    @Override
    public Song getByName(String song) throws DAOException {
        return null;
    }

    @Override
    Long getKey(Song entity) {
        return null;
    }
}

