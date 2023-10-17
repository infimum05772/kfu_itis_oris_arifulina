package ru.kpfu.itis.arifulina.net.servlet;

import com.google.gson.Gson;
import ru.kpfu.itis.arifulina.net.dto.ServiceDto;
import ru.kpfu.itis.arifulina.net.model.Service;
import ru.kpfu.itis.arifulina.net.service.MasterService;
import ru.kpfu.itis.arifulina.net.service.ServiceService;
import ru.kpfu.itis.arifulina.net.service.impl.MasterServiceImpl;
import ru.kpfu.itis.arifulina.net.service.impl.ServiceServiceImpl;

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
    public static final ServiceService serviceService = new ServiceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] master = req.getParameter("master").split(" ");
        List<String> serviceNames = serviceService.getAllByMaster(master[0], master[1]).stream().map(ServiceDto::getName).collect(Collectors.toList());
        Gson gson = new Gson();
        String jsonServiceNames = gson.toJson(serviceNames);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonServiceNames);
    }
}
