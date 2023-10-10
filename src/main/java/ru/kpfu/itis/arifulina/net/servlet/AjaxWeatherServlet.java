package ru.kpfu.itis.arifulina.net.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.arifulina.net.client.HTTPClientImp;
import ru.kpfu.itis.arifulina.net.client.HttpClientException;
import ru.kpfu.itis.arifulina.net.dto.WeatherDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ajaxWeatherServlet", urlPatterns = "/ajax/weather")
public class AjaxWeatherServlet extends HttpServlet {
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String API_KEY = "d9150eeddee7ab7195229541fcc66ad8";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HTTPClientImp httpClient = new HTTPClientImp();
        Map<String, String> params = new HashMap<>();
        params.put("q", req.getParameter("city"));
        params.put("appid", API_KEY);
        params.put("units", "metric");
        resp.setContentType("text/plain");

        try {
            String weatherStr = httpClient.get(URL, params, null);
            WeatherDto weatherDto = parseJSON(weatherStr);
            req.setAttribute("weather", weatherDto);
            resp.getWriter().write(weatherDto.toString());
        } catch (HttpClientException e) {
            resp.getWriter().write("smth went wrong :(\n" +
                    "check if you entered city name correctly");
        }
    }

    public WeatherDto parseJSON(String weatherStr) {
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
}
