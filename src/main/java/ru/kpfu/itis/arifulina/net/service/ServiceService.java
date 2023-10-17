package ru.kpfu.itis.arifulina.net.service;

import ru.kpfu.itis.arifulina.net.dto.MasterDto;
import ru.kpfu.itis.arifulina.net.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    List<ServiceDto> getAllByMaster(String name, String surname);
}
