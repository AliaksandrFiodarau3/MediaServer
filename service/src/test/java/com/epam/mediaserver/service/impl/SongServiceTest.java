/*
package com.epam.mediaserver.service.impl;

import static junit.framework.TestCase.assertTrue;

import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Album;
import com.epam.mediaserver.entity.Song;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import org.junit.Before;
import org.junit.Test;

public class SongServiceTest {

    String title = "Title";
    String duration = "02:02:02";
    String albumTitle = "In Utero";
    String price = "2";
    private ConnectionPool connectionPool;

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String album, String duration, String price)
        throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getSongService().add(title, album, duration, price);
    }


    public void edit(Long id, String title, String album, String duration, String price)
        throws ServiceException, ValidateException {

        ServiceFactory.getSongService().edit(id, title, album, duration, price);
    }

    public void delete(Long id) throws ServiceException {
        ServiceFactory.getSongService().delete(id);
    }

    public boolean check(Long id, String title, Album album, String duration, String price) throws DAOException {

        Song song = (Song) SqlFactory.getSongDao().getById(id);

        if (song == null) {
            return false;
        }
        if (song.getTitle().equals(title) && song.getAlbum().equals(album)
            && song.getDuration().equals(duration) && song.getPrice() == Integer.parseInt(price)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {

        add(title, albumTitle, duration, price);

        Song song = SqlFactory.getSongDao().getByName(title);
        Long id = song.getId();

        Album album = SqlFactory.getAlbumDao().getByName(albumTitle);

        boolean isAdd = check(id, title, album, duration, price);

        price = "3";

        edit(id, title, albumTitle, duration, price);

        boolean isEdit = check(id, title, album, duration, price);

        delete(id);

        boolean isDelete = check(id, title, album, duration, price);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}

*/
