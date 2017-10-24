package by.epam.service.impl;

import by.epam.bean.Bonus;
import by.epam.constant.Error;
import by.epam.dao.SqlFactory;
import by.epam.exeption.ServiceException;
import by.epam.exeption.ValidateException;
import by.epam.exeption.dao.DAOException;
import by.epam.util.Validation;
import org.apache.log4j.Logger;

import java.util.List;

public class BonusTableService {

    private static final Logger LOGGER = Logger.getLogger(BonusTableService.class);

    public void add( String title,  String description, String discount, String code) throws ValidateException, ServiceException {

        try {
            if (Validation.bonusCheck(title, description, discount, code)) {

                Bonus bonus = new Bonus();

                bonus.setTitle(title);
                bonus.setDescription(description);
                bonus.setDiscount(Integer.parseInt(discount));
                bonus.setCode(code);

                SqlFactory.getBonusDao().add(bonus);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }catch (NumberFormatException e){
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }

    }

    public void edit(int id, String title, String description, String discount, String code) throws ServiceException, ValidateException {

        try {

            Bonus bonus = (Bonus) SqlFactory.getBonusDao().getById(id);

            if (Validation.bonusCheck(title, description, discount, code)) {

                bonus.setTitle(title);
                bonus.setDescription(description);
                bonus.setDiscount(Integer.parseInt(discount));
                bonus.setCode(code);

                SqlFactory.getBonusDao().update(bonus);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public void delete(int id) throws ServiceException {

        try {
            Bonus bonus = (Bonus) SqlFactory.getBonusDao().getById(id);
            SqlFactory.getBonusDao().delete(bonus);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Bonus> getAll() throws ServiceException {

        List<Bonus> bonuses = null;

        try {
            bonuses = SqlFactory.getBonusDao().getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return bonuses;
    }


}
