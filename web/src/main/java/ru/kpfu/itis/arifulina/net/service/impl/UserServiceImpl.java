package ru.kpfu.itis.arifulina.net.service.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;
import ru.kpfu.itis.arifulina.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.arifulina.net.dto.UserDto;
import ru.kpfu.itis.arifulina.net.model.User;
import ru.kpfu.itis.arifulina.net.service.UserService;
import ru.kpfu.itis.arifulina.net.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    public static final Dao<User> dao = new UserDaoImpl();
    @Override
    public List<UserDto> getAll() {
        return dao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLastname()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public void save(User user) throws DaoException {
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        dao.save(user);
    }
}
