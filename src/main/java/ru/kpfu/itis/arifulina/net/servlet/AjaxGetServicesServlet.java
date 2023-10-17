package ru.kpfu.itis.arifulina.net.servlet;

import com.google.gson.Gson;
import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.impl.ServiceDaoImpl;
import ru.kpfu.itis.arifulina.net.model.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ajaxGetServicesServlet", urlPatterns = "/ajax/services")
public class AjaxGetServicesServlet extends HttpServlet {
    public static final Dao<Service> serviceDao = new ServiceDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int masterId = Integer.parseInt(req.getParameter("master"));
        List<Service> serviceNames = ((ServiceDaoImpl) serviceDao).getAllByMaster(masterId);
        Gson gson = new Gson();
        String jsonServiceNames = gson.toJson(serviceNames);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonServiceNames);
    }
}
