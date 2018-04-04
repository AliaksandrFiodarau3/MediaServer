package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.GenreDao;
import com.epam.mediaserver.entity.Genre;
import com.epam.mediaserver.exeption.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * #GenreDao interface implementation for the MySQL db {@link GenreDao}
 * #AbstractModelDao extends for call CRUD commands for the MySQL db
 */

@Repository
public class GenreDaoImpl extends AbstractModelDao<Genre, Long> implements GenreDao {

    private static final Logger LOGGER = LogManager.getLogger(GenreDaoImpl.class);

    private static final String
        CREATE_QUERY =
        "insert into t_genre (genre_description, genre_title, genre_image ) values (?,?,?);";
    private static final String SELECT_QUERY = "select * from t_genre;";
    private static final String SELECT_QUERY_WITH_ID = "select * from t_genre where genre_id = ?;";
    private static final String SELECT_QUERY_WITH_NAME = "SELECT * FROM t_genre WHERE genre_title = ?;";
    private static final String UPDATE_QUERY = "update t_genre set genre_description = ? where genre_title = ?;";
    private static final String DELETE_QUERY = "DELETE FROM t_genre WHERE genre_title = ?;";

    private static final String GENRE_ID = "genre_id";
    private static final String GENRE_TITLE = "genre_title";
    private static final String GENRE_DESCRIPTION = "genre_description";
    private static final String GENRE_IMAGE = "genre_image";

    public GenreDaoImpl() {
        super(Genre.class);
    }


    @Override
    public Genre getByName(String title) throws DAOException {
        return null;
    }

    @Override
    public Long getKey(Genre entity) {
        return null;
    }
}
