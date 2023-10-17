package ru.kpfu.itis.arifulina.net.service.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.impl.MasterDaoImpl;
import ru.kpfu.itis.arifulina.net.dao.impl.ServiceDaoImpl;
import ru.kpfu.itis.arifulina.net.dto.MasterDto;
import ru.kpfu.itis.arifulina.net.dto.ServiceDto;
import ru.kpfu.itis.arifulina.net.model.Master;
import ru.kpfu.itis.arifulina.net.model.Service;
import ru.kpfu.itis.arifulina.net.service.ServiceService;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceServiceImpl implements ServiceService {
    public static final Dao<Service> serviceDao = new ServiceDaoImpl();
    public static final Dao<Master> masterDao = new MasterDaoImpl();

    @Override
    public List<ServiceDto> getAllByMaster(String name, String surname) {
        Master master = ((MasterDaoImpl) masterDao).getByNameSurname(name, surname);
        return ((ServiceDaoImpl) serviceDao).getAllByMaster(master.getMasterId()).stream().map(
                        u -> new ServiceDto(u.getName()))
                .collect(Collectors.toList());
    }
}
