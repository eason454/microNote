package com.asiainfo.service;

import java.util.List;

/**
 * Created by eason on 2017/2/21.
 */
public interface ICRUDService<T> {
    List<?> listAll();

    T getById(Long id);

    T saveOrUpdate(T domainObject);

    void delete(Long id);
}
