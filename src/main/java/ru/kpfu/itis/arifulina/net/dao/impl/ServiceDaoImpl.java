package ru.kpfu.itis.arifulina.net.dao.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;
import ru.kpfu.itis.arifulina.net.model.Master;
import ru.kpfu.itis.arifulina.net.model.Service;
import ru.kpfu.itis.arifulina.net.util.DBConnectionBeautySaloonUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements Dao<Service> {
    public static final Connection connection = DBConnectionBeautySaloonUtil.getConnection();

    @Override
    public Service get(int id) throws DaoException {
        try {
            String sqlService = "select * from services where service_id=?";
            PreparedStatement preparedStatementService = connection.prepareStatement(sqlService);
            preparedStatementService.setInt(1, id);
            ResultSet resultSet = preparedStatementService.executeQuery();
            Service service = null;
            if (resultSet.next()) {
                service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("duration")
                );
            }
            return service;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Service> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");
            List<Service> services = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            new Service(
                                    resultSet.getInt("service_id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("duration")
                            )
                    );
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Service service) {
        //реализовывать в рамках кр нет особого смысла
    }

    public Service getByName(String name) {
        try {
            String sql = "SELECT * FROM services where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Service service = null;
            if (resultSet.next()) {
                service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("duration")
                );
            }
            return service;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Service> getAllByMaster(int masterId) {
        try {
            String sql = "SELECT * FROM services inner join master_service on services.service_id=master_service.service_id where master_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, masterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Service> services = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            new Service(
                                    resultSet.getInt("service_id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("duration")
                            )
                    );
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
