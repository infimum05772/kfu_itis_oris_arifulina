package ru.kpfu.itis.arifulina.net.servlet;

import ru.kpfu.itis.arifulina.net.dao.Dao;
import ru.kpfu.itis.arifulina.net.dao.impl.MasterDaoImpl;
import ru.kpfu.itis.arifulina.net.model.Master;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "beautySaloonServlet", urlPatterns = "/beauty")
public class BeautySaloonServlet extends HttpServlet {
    public static final Dao<Master> masterDao = new MasterDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("masters", masterDao.getAll());
        req.getRequestDispatcher("test/main.ftl").forward(req, resp);
    }
}
