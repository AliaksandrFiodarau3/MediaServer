/*
package com.epam.mediaserver.service.impl;

import static junit.framework.TestCase.assertTrue;

import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Artist;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class AlbumServiceTest {

    String title = "Title";
    String description = "Description";
    String artistTitle = "Metallica";
    String year = "1990";
    String image = "image";
    private ConnectionPool connectionPool;

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String artist, String year, String description, String image)
        throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getAlbumService().add(artist, title, year, description, image);
    }


    public void edit(int id, String title, String artist, String year, String description, String image)
        throws ServiceException, ValidateException {

        ServiceFactory.getAlbumService().edit(id, title, artist, year, description, image);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getAlbumService().delete(id);
    }

    public boolean check(int id, String title, Artist artist, String description, String image) throws DAOException {

        Album album = (Album) SqlFactory.getAlbumDao().getById(id);

        if (album == null) {
            return false;
        }
        if (album.getTitle().equals(title) && album.getDescription().equals(description)
            && album.getArtist().equals(artist) && album.getImage().equals(image)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {

        add(title, artistTitle, year, description, image);

        Album album = SqlFactory.getAlbumDao().getByName(title);
        int id = album.getId();

        Artist artist = SqlFactory.getArtistDao().getByName(artistTitle);

        boolean isAdd = check(id, title, artist, description, image);

        description = "New description";

        edit(id, title, artistTitle, year, description, image);

        boolean isEdit = check(id, title, artist, description, image);

        delete(id);

        boolean isDelete = check(id, title, artist, description, image);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
*/
