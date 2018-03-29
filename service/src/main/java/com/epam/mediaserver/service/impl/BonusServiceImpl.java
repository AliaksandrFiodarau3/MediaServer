package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.BonusDao;
import com.epam.mediaserver.entity.Bonus;
import com.epam.mediaserver.service.BonusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BonusServiceImpl extends CrudServiceImpl<Bonus, Long> implements BonusService {

    private static final Logger LOGGER = LogManager.getLogger(BonusServiceImpl.class);

    @Autowired
    public BonusServiceImpl(BonusDao bonusDao) {
        super(bonusDao);
    }

}
