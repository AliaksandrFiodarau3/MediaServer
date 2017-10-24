package service;

import by.epam.bean.Bonus;
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

public class GenreServiceTest {

    private ConnectionPool connectionPool;

    String title = "Title";
    String description = "Description";
    String image = "image";

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String description, String image) throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getGenreService().add(title, description, image);
    }


    public void edit(int id, String title, String description, String image) throws ServiceException, ValidateException {

        ServiceFactory.getGenreService().edit(id, title, description, image);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getGenreService().delete(id);
    }

    public boolean check(int id, String title, String description) throws DAOException {

        Genre genre = (Genre) SqlFactory.getGenreDao().getById(id);

        if (genre == null) {
            return false;
        }
        if (genre.getTitle().equals(title) && genre.getDescription().equals(description)) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {


        add(title, description, image);

        Genre genre = SqlFactory.getGenreDao().getByName(title);
        int id = genre.getId();

        boolean isAdd = check(id, title, description);

        description = "New description";

        edit(id, title, description, image);

        boolean isEdit = check(id, title, description);

        delete(id);

        boolean isDelete = check(id, title, description);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
