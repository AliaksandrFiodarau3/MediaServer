package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.RoleDao;
import com.epam.mediaserver.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractModelDao<Role, Long> implements RoleDao  {


    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Long getKey(Role entity) {
        return null;
    }

}
