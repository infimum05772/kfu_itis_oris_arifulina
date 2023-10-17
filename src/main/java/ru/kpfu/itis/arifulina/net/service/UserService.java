package ru.kpfu.itis.arifulina.net.service;

import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;
import ru.kpfu.itis.arifulina.net.dto.UserDto;
import ru.kpfu.itis.arifulina.net.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto get(int id);

    void save(User user) throws DaoException;
}
