package ru.kpfu.itis.arifulina.net.service.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.impl.MasterDaoImpl;
import ru.kpfu.itis.arifulina.net.dto.MasterDto;
import ru.kpfu.itis.arifulina.net.dto.UserDto;
import ru.kpfu.itis.arifulina.net.model.Master;
import ru.kpfu.itis.arifulina.net.service.MasterService;

import java.util.List;
import java.util.stream.Collectors;

public class MasterServiceImpl implements MasterService {
    public static final Dao<Master> dao = new MasterDaoImpl();

    @Override
    public List<MasterDto> getAll() {
        return dao.getAll().stream().map(
                        u -> new MasterDto(u.getName(), u.getSurname()))
                .collect(Collectors.toList());
    }
}
