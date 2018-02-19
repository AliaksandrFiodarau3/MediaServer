package service;

import by.epam.bean.Album;
import by.epam.bean.Song;
import by.epam.dao.SqlFactory;
import by.epam.dao.impl.pool.ConnectionPool;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.ConnectionPoolException;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static junit.framework.TestCase.assertTrue;

public class SongServiceTest {

    private ConnectionPool connectionPool;

    String title = "Title";
    String duration = "02:02:02";
    String albumTitle = "In Utero";
    String price = "2";

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String album, String duration, String price) throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getSongService().add(title, album, duration, price);
    }


    public void edit(int id, String title, String album, String duration, String price) throws ServiceException, ValidateException {

        ServiceFactory.getSongService().edit(id, title, album, duration, price);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getSongService().delete(id);
    }

    public boolean check(int id, String title, Album album, String duration, String price) throws DAOException {

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
        int id = song.getId();

        Album album = SqlFactory.getAlbumDao().getByName(albumTitle);


        boolean isAdd = check(id, title,  album, duration, price);

        price = "3";

        edit(id,title,  albumTitle, duration, price);

        boolean isEdit = check(id, title,  album, duration, price);

        delete(id);

        boolean isDelete = check(id, title,  album, duration, price);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}

