package ru.kpfu.itis.arifulina.net.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "weatherLoginServlet", urlPatterns = "/wlogin")
public class WeatherLoginServlet extends HttpServlet {
    public static final String USERS_PATH = "C:\\Users\\rarif\\Desktop\\home\\javka\\httpclient\\src\\main\\java\\ru\\kpfu\\itis\\arifulina\\net\\res\\users_database.csv";
    public static final String COLUMN_DELIMITER = ",";
    public static final Logger LOGGER = LoggerFactory.getLogger(WeatherLoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("weather_web\\login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (isRegistered(login, password)) {
            LOGGER.info("User with login {} logged in", login);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(24 * 60);

            Cookie cookie = new Cookie("username", login);
            cookie.setMaxAge(24 * 3600);
            resp.addCookie(cookie);

            resp.sendRedirect("/city");
        } else {
            resp.sendRedirect("/wlogin");
        }
    }

    public boolean isRegistered(String username, String password) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COLUMN_DELIMITER);
                if (username.equalsIgnoreCase(values[1]) && password.equals(values[2])) {
                    return true;
                }
            }
            return false;
        }
    }
}
