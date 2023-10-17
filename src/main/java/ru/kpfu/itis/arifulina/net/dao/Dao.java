package ru.kpfu.itis.arifulina.net.dao;

import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {
    T get(int id) throws DaoException;

    List<T> getAll();

    void save(T t) throws DaoException;
}
