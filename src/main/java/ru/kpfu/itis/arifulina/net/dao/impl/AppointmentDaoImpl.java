package ru.kpfu.itis.arifulina.net.dao.impl;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;
import ru.kpfu.itis.arifulina.net.model.Appointment;
import ru.kpfu.itis.arifulina.net.model.Service;
import ru.kpfu.itis.arifulina.net.util.DBConnectionBeautySaloonUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDaoImpl implements Dao<Appointment> {
    public static final Dao<Service> serviceDao = new ServiceDaoImpl();
    public static final Connection connection = DBConnectionBeautySaloonUtil.getConnection();
    @Override
    public Appointment get(int id) {
        return null;
    }

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public void save(Appointment appointment) throws DaoException {
        try {
            int duration = serviceDao.get(appointment.getServiceId()).getDuration();
            LocalDateTime startTime = appointment.getAppointmentTime().toLocalDateTime();
            LocalDateTime endTime = startTime.plusMinutes(duration);

            String sqlJoin = "select * from appointments inner join services on appointments.service_id=services.service_id where master_id=?";
            PreparedStatement preparedStatementJoin = connection.prepareStatement(sqlJoin);
            preparedStatementJoin.setInt(1, appointment.getMasterId());
            ResultSet resultSet = preparedStatementJoin.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    LocalDateTime currStartTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                    LocalDateTime currEndTime = currStartTime.plusMinutes(resultSet.getInt("duration"));
                    if (!(endTime.isBefore(currStartTime) || currEndTime.isBefore(startTime))) {
                        throw new DaoException("appointment for this time isn't possible");
                    }
                }
            }

            String sqlInsert = "insert into appointments(customer_phone, master_id, service_id, appointment_time) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, appointment.getCustomerPhone());
            preparedStatement.setInt(2, appointment.getMasterId());
            preparedStatement.setInt(3, appointment.getServiceId());
            preparedStatement.setTimestamp(4, appointment.getAppointmentTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
