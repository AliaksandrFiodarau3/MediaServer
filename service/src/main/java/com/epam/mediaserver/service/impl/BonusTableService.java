package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.constant.Error;
import com.epam.mediaserver.dao.impl.SqlBonusDao;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.exception.ValidateException;
import com.epam.mediaserver.exeption.DAOException;
import com.epam.mediaserver.service.BonusService;
import com.epam.mediaserver.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BonusTableService implements BonusService{

    @Autowired
    private SqlBonusDao bonusDao;


    private static final Logger LOGGER = LogManager.getLogger(BonusTableService.class);

    public void add(String title, String description, String discount, String code)
        throws ValidateException, ServiceException {

        try {
            if (Validation.bonusCheck(title, description, discount, code)) {

                Bonus bonus = new Bonus();

                bonus.setTitle(title);
                bonus.setDescription(description);
                bonus.setDiscount(Integer.parseInt(discount));
                bonus.setCode(code);

                bonusDao.add(bonus);

            } else {
                LOGGER.info(Error.VALIDATION);
                throw new ValidateException(Error.VALIDATION);
            }
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        } catch (NumberFormatException e) {
            LOGGER.info(Error.VALIDATION);
            throw new ValidateException(Error.VALIDATION);
        }

    }

    public void edit(int id, String title, String description, String discount, String code)
        throws ServiceException, ValidateException {

        try {

            Bonus bonus = bonusDao.getById(id);

            if (Validation.bonusCheck(title, description, discount, code)) {

                bonus.setTitle(title);
                bonus.setDescription(description);
                bonus.setDiscount(Integer.parseInt(discount));
                bonus.setCode(code);

                bonusDao.update(bonus);

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
            Bonus bonus = (Bonus) bonusDao.getById(id);
            bonusDao.delete(bonus);
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }
    }

    public List<Bonus> getAll() throws ServiceException {

        List<Bonus> bonuses = null;

        try {
            bonuses = bonusDao.getAll();
        } catch (DAOException e) {
            LOGGER.error(Error.DAO_EXCEPTION);
            throw new ServiceException(Error.DAO_EXCEPTION);
        }

        return bonuses;
    }


}
