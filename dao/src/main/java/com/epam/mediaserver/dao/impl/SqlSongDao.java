package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.AbstractModelDao;
import com.epam.mediaserver.dao.SongDao;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Model;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * #SongDao interface implementation for the MySQL db {@link SongDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class SqlSongDao extends AbstractModelDao<Song> implements SongDao {

    @Autowired
    private SqlAlbumDao albumDao;

    private static final Logger LOGGER = LogManager.getLogger(SqlSongDao.class);

    private static final String
        CREATE_QUERY =
        "INSERT INTO t_song (album_id, song_duration, song_price, song_title) VALUES (?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT * FROM t_song";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_song where song_id = ?;";
    private static final String
        UPDATE_QUERY =
        "update t_song set album_id = ?, song_duration = ? ,song_price = ?, song_title = ? where song_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_song WHERE song_id = ?;";
    private static final String
        BY_ALBUM_QUERY =
        "SELECT * FROM t_song WHERE album_id = (SELECT album_id FROM t_album WHERE album_title = ?);";
    private static final String BY_NAME_QUERY = "SELECT * FROM t_song WHERE song_title = ?;";

    private static final String SONG_ID = "song_id";
    private static final String ALBUM_ID = "album_id";
    private static final String SONG_TITLE = "song_title";
    private static final String SONG_DURATION = "song_duration";
    private static final String SONG_PRICE = "song_price";

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    protected String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    protected String getSelectQueryWithID() {
        return SELECT_QUERY_WITH_ID;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected int preparedStatementForCreate(Connection con, Song model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Song song = (Song) model;
        ps.setInt(1, song.getAlbum().getId());
        ps.setTime(2, song.getDuration());
        ps.setInt(3, song.getPrice());
        ps.setString(4, song.getTitle());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForUpdate(Connection con, Model model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);

        Song song = (Song) model;
        ps.setInt(1, song.getAlbum().getId());
        ps.setTime(2, song.getDuration());
        ps.setInt(3, song.getPrice());
        ps.setString(4, song.getTitle());
        ps.setInt(5, song.getId());

        return ps.executeUpdate();
    }

    @Override
    protected int preparedStatementForDelete(Connection con, Song model, String query) throws SQLException {
        PreparedStatement ps = con.prepareStatement(getDeleteQuery());

        Song song = (Song) model;
        ps.setInt(1, song.getId());

        return ps.executeUpdate();
    }

    @Override
    protected Song parseResult(ResultSet rs) throws DAOException {
        Song song = new Song();

        try {
            int id = rs.getInt(SONG_ID);
            song.setId(id);

            Album album = albumDao.getById(rs.getInt(ALBUM_ID));
            song.setAlbum(album);
            song.setTitle(rs.getString(SONG_TITLE));
            song.setDuration(rs.getTime(SONG_DURATION));
            song.setPrice(rs.getInt(SONG_PRICE));

        } catch (SQLException e) {
            LOGGER.error("SQL Exception");
            throw new DAOException("SQL Exception");
        }
        return song;
    }


    @Override
    public List<Song> getByAlbum(String album) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_ALBUM_QUERY);) {

            ps.setString(1, album);
            ResultSet rs = ps.executeQuery();

            List<Song> list = new ArrayList<>();
            while (rs.next()) {
                Model song = parseResult(rs);
                list.add((Song) song);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        }


    }

    @Override
    public Song getByName(String name) throws DAOException {

        try (Connection con = ConnectionPool.takeConnection();
             PreparedStatement ps = con.prepareStatement(BY_NAME_QUERY)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            Song song = null;
            if (rs.next()) {
                song = parseResult(rs);
            }

            return song;
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection is not open", e);
            throw new DAOException("Connection is not open");
        } catch (SQLException e) {
            LOGGER.error("SQL Exception", e);
            throw new DAOException("SQL Exception");
        }

    }
}

