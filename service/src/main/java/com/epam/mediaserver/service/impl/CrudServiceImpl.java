package com.epam.mediaserver.service.impl;

import com.epam.mediaserver.dao.CrudDao;
import com.epam.mediaserver.service.CrudService;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;

public abstract class CrudServiceImpl<T, K extends Serializable> implements CrudService<T, K>{

    private CrudDao<T, K> dao;

    CrudServiceImpl(CrudDao<T, K> dao) {
        this.dao = dao;
    }

    CrudDao<T, K> getDao() {
        return dao;
    }

    @Transactional
    @Override
    public void create(T entity) {
        dao.create(entity);
    }

    @Transactional
    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Transactional
    @Override
    public T find(K key) {
        return dao.find(key);
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

}
