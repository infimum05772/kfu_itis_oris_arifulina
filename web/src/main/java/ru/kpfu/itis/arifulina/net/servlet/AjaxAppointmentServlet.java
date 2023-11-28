package ru.kpfu.itis.arifulina.net.servlet;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.exceptions.DaoException;
import ru.kpfu.itis.arifulina.net.dao.impl.AppointmentDaoImpl;
import ru.kpfu.itis.arifulina.net.dao.impl.MasterDaoImpl;
import ru.kpfu.itis.arifulina.net.dao.impl.ServiceDaoImpl;
import ru.kpfu.itis.arifulina.net.model.Appointment;
import ru.kpfu.itis.arifulina.net.model.Master;
import ru.kpfu.itis.arifulina.net.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "ajaxAppointmentServlet", urlPatterns = "/ajax/appointment")
public class AjaxAppointmentServlet extends HttpServlet {
    public static final Dao<Appointment> appointmentDao = new AppointmentDaoImpl();
    public static final Dao<Master> masterDao = new MasterDaoImpl();
    public static final Dao<Service> serviceDao = new ServiceDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Timestamp timestamp = Timestamp.valueOf(req.getParameter("date") + " " + req.getParameter("time") + ":00");
        int masterId = Integer.parseInt(req.getParameter("master"));
        int serviceId = Integer.parseInt(req.getParameter("service"));
        Master master = masterDao.get(masterId);
        Appointment appointment = new Appointment(
                req.getParameter("phone"),
                timestamp,
                masterId,
                serviceId
        );
        try {
            appointmentDao.save(appointment);
            resp.getWriter().write("you have made an appointment to " + master.getName() + " " + master.getSurname() + " for " + serviceDao.get(serviceId).getName() + " at " + timestamp);
        } catch (DaoException e) {
            resp.getWriter().write(e.getMessage());
        }
    }
}
