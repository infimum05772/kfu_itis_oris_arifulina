package ru.kpfu.itis.arifulina.net.server;

import ru.kpfu.itis.arifulina.net.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    public static final List<UserDto> USERS = List.of(
            new UserDto("ivan", "ivanov"),
            new UserDto("ivan", "stepanov"),
            new UserDto("stepan", "ivanov")
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", USERS);
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
