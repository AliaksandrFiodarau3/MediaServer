package com.epam.mediaserver.builder;

import com.epam.mediaserver.entity.Model;

public interface Builder<T extends Model> {

    T build();

}
