package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.RoleDao;
import com.epam.mediaserver.entity.Role;
import com.epam.mediaserver.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role, Long> implements RoleService {

    RoleServiceImpl(RoleDao dao) {
        super(dao);
    }
}
