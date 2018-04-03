package com.epam.mediaserver.dao;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T, K extends Serializable> {

    void create(T entity);

    void update(T entity);

    void delete(K id);

    T find(K key);

    List<T> findAll();

}
