package ru.kpfu.itis.arifulina.net.servlet;

import ru.kpfu.itis.arifulina.net.dto.UserDto;
import ru.kpfu.itis.arifulina.net.service.UserService;
import ru.kpfu.itis.arifulina.net.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    public static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
