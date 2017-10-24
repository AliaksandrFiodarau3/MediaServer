package service;

import by.epam.bean.Artist;
import by.epam.bean.Genre;
import by.epam.dao.SqlFactory;
import by.epam.dao.impl.pool.ConnectionPool;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.ConnectionPoolException;
import by.epam.exeption.dao.DAOException;
import by.epam.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ArtistServiceTest {

    private ConnectionPool connectionPool;

    String title = "Title";
    String description = "Description";
    String genreTitle = "Grunge";
    String image = "image";

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String genre, String description, String image) throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getArtistService().add(title,genre,description,image);
    }


    public void edit(int id, String title, String genre, String description, String image) throws ServiceException, ValidateException {

        ServiceFactory.getArtistService().edit(id, title,description,image);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getArtistService().delete(id);
    }

    public boolean check(int id, String title, Genre genre, String description, String image) throws DAOException {

        Artist artist = (Artist) SqlFactory.getArtistDao().getById(id);

        if (artist == null) {
            return false;
        }
        if (artist.getTitle().equals(title) && artist.getDescription().equals(description)
                && artist.getGenre().equals(genre) && artist.getImage().equals(image)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {


        add(title, genreTitle, description, image);

        Artist artist = SqlFactory.getArtistDao().getByName(title);
        int id = artist.getId();

        Genre genre = SqlFactory.getGenreDao().getByName(genreTitle);


        boolean isAdd = check(id, title, genre ,description ,image);

        description = "New description";

        edit(id, title, genreTitle, description, image);

        boolean isEdit = check(id,  title, genre, description, image);

        delete(id);

        boolean isDelete = check(id, title, genre, description, image);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
