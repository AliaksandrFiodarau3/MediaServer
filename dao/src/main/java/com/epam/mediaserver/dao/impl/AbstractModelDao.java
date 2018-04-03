package com.epam.mediaserver.dao.impl;

import com.epam.mediaserver.dao.CrudDao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract class for CRUD model DAO
 */

public abstract class AbstractModelDao<T, K extends Serializable> implements CrudDao<T, K> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    @SuppressWarnings("unused")
    private AbstractModelDao() {
    }

    public AbstractModelDao(Class<T> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(find(id));
    }

    @Override
    public T find(K key) {
        return entityManager.find(type, key);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("select t from " + type.getSimpleName() + " t ORDER BY t.id ASC", type).getResultList();
    }

    abstract K getKey(T entity);

}
