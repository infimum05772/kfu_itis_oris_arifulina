package ru.kpfu.itis.arifulina.net.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.arifulina.net.client.HTTPClientImp;
import ru.kpfu.itis.arifulina.net.client.HttpClientException;
import ru.kpfu.itis.arifulina.net.dto.WeatherDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "weatherCityServlet", urlPatterns = "/city")
public class WeatherCityServlet extends HttpServlet {
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String API_KEY = "d9150eeddee7ab7195229541fcc66ad8";
    public static final Logger LOGGER = LoggerFactory.getLogger(WeatherLoginServlet.class);
    private String user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionUser = req.getSession().getAttribute("username").toString();
        Cookie[] cookies = req.getCookies();
        if (sessionUser == null) {
            String cookieUsername = getCookieUsername(cookies);
            if (cookieUsername == null) {
                resp.sendRedirect("/wlogin");
            } else {
                user = cookieUsername;
                req.setAttribute("username", cookieUsername);
                req.getRequestDispatcher("weather_web\\city.ftl").forward(req, resp);
            }
        } else {
            user = sessionUser;
            req.setAttribute("username", sessionUser);
            req.getRequestDispatcher("weather_web\\city.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        req.setAttribute("city", city);

        HTTPClientImp httpClient = new HTTPClientImp();
        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appid", API_KEY);
        params.put("units", "metric");

        try {
            long startReqTime = System.nanoTime();
            String weatherStr = httpClient.get(URL, params, null);
            long endReqTime = System.nanoTime();
            WeatherDto weatherDto = parseJSON(weatherStr);
            LOGGER.info("User with login {} sent request for city {} at time {}, duration: {} ns", user, city, LocalDateTime.now(), endReqTime - startReqTime);
            req.setAttribute("weather", weatherDto);
        } catch (HttpClientException e) {
            req.setAttribute("err", "something went wrong :( please, check if your city is entered correctly");
        }

        req.getRequestDispatcher("weather_web\\weather.ftl").forward(req, resp);
    }

    public WeatherDto parseJSON(String weatherStr) {
        String[] values = new String[3];
        JsonObject weather = JsonParser.parseString(weatherStr).getAsJsonObject();
        JsonObject main = weather.get("main").getAsJsonObject();
        String temp = main.get("temp").getAsString();
        String humidity = main.get("humidity").getAsString();
        String precipitation = weather
                .get("weather")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("description")
                .getAsString();
        return new WeatherDto(temp, humidity, precipitation);
    }

    private String getCookieUsername(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equalsIgnoreCase(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
