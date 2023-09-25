package ru.kpfu.itis.arifulina.net.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.arifulina.net.client.HTTPClientImp;
import ru.kpfu.itis.arifulina.net.client.HttpClientException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "weatherCityServlet", urlPatterns = "/city")
public class WeatherCityServlet extends HttpServlet {
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String API_KEY = "d9150eeddee7ab7195229541fcc66ad8";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionUser = req.getSession().getAttribute("username").toString();
        if (sessionUser == null) {
            resp.sendRedirect("/wlogin");
        } else {
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
            String weatherStr = httpClient.get(URL, params, null);
            String[] values = parseJSON(weatherStr);
            req.setAttribute("temperature", values[0]);
            req.setAttribute("humidity", values[1]);
            req.setAttribute("precipitation", values[2]);
        } catch (HttpClientException e) {
            req.setAttribute("err", "something went wrong :( please, check if your city is entered correctly");
        }

        req.getRequestDispatcher("weather_web\\weather.ftl").forward(req, resp);
    }

    public String[] parseJSON(String weatherStr) {
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
        values[0] = temp;
        values[1] = humidity;
        values[2] = precipitation;
        return values;
    }
}
