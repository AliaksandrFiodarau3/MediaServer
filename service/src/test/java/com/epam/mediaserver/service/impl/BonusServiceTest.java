/*
package com.epam.mediaserver.service.impl;

import static junit.framework.TestCase.assertTrue;

import com.epam.mediaserver.dao.SqlFactory;
import com.epam.mediaserver.dao.impl.pool.ConnectionPool;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.ConnectionPoolException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

public class BonusServiceTest {

    String title = "Title";
    String description = "Description";
    String code = "SkiDkA11";
    String discount = "15";
    private ConnectionPool connectionPool;

    @Before
    public void createPool() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolDate();

    }

    public void add(String title, String description, String discount, String code)
        throws ServiceException, ValidateException, DAOException {

        ServiceFactory.getBonusService().add(title, description, discount, code);
    }


    public void edit(int id, String title, String description, String discount, String code)
        throws ServiceException, ValidateException {

        ServiceFactory.getBonusService().edit(id, title, description, discount, code);
    }

    public void delete(int id) throws ServiceException {
        ServiceFactory.getBonusService().delete(id);
    }

    public boolean check(int id, String title, String description, String discount, String code) throws DAOException {

        Bonus bonus = (Bonus) SqlFactory.getBonusDao().getById(id);

        if (bonus == null) {
            return false;
        }
        if (bonus.getTitle().equals(title) && bonus.getDescription().equals(description)
            && bonus.getDiscount() == Integer.parseInt(discount) && bonus.getCode().equals(code)) {
            return true;
        } else {
            return false;
        }

    }


    @Test
    public void test() throws ValidateException, DAOException, ServiceException, ConnectionPoolException {
//

        add(title, description, discount, code);

        Bonus bonus = SqlFactory.getBonusDao().getByCode(code);
        int id = bonus.getId();

        boolean isAdd = check(id, title, description, discount, code);

        discount = "10";

        edit(id, title, description, discount, code);

        boolean isEdit = check(id, title, description, discount, code);

        delete(id);

        boolean isDelete = check(id, title, description, discount, code);

        boolean finalTest = false;

        if (isAdd && isEdit && !isDelete) {
            finalTest = true;
        }
        assertTrue(finalTest);
    }

}
*/
