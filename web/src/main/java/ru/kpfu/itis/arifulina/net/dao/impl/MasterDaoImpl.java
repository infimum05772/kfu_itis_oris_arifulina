package ru.kpfu.itis.arifulina.net.dao.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.model.Master;
import ru.kpfu.itis.arifulina.net.model.User;
import ru.kpfu.itis.arifulina.net.util.DBConnectionBeautySaloonUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDaoImpl implements Dao<Master> {
    public static final Connection connection = DBConnectionBeautySaloonUtil.getConnection();

    @Override
    public Master get(int id) {
        try {
            String sql = "SELECT * FROM masters where master_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Master master = null;
            if (resultSet.next()) {
                master = new Master(
                        resultSet.getInt("master_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("phone")
                );
            }
            return master;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Master> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM masters");
            List<Master> masters = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    masters.add(
                            new Master(
                                    resultSet.getInt("master_id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("surname"),
                                    resultSet.getString("phone")
                            )
                    );
                }
            }
            return masters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Master master) {
        //реализовывать в рамках кр нет особого смысла
    }
}
