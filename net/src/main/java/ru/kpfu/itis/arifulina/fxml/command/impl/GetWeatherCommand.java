package ru.kpfu.itis.arifulina.fxml.command.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.concurrent.Task;
import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;
import ru.kpfu.itis.arifulina.http_client.HTTPClientImp;
import ru.kpfu.itis.arifulina.http_client.HttpClientException;

import java.util.HashMap;
import java.util.Map;

public class GetWeatherCommand extends AbstractCommand {
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String API_KEY = "d9150eeddee7ab7195229541fcc66ad8";
    public static final HTTPClientImp httpClient = new HTTPClientImp();

    @Override
    public void execute(String[] attributes) throws CommandExecutionException {
        if (!app.isActive()) {
            throw new CommandExecutionException(BotStrings.MESSAGE_BEFORE_START);
        } else {
            if (attributes.length < 1) {
                throw new CommandExecutionException(BotStrings.INVALID_ARGS_WEATHER_MESSAGE);
            } else {
                Task<String> task = new Task<>() {
                    @Override
                    protected String call() throws Exception {
                        StringBuilder res = new StringBuilder();
                        for (String attr : attributes) {
                            Map<String, String> params = new HashMap<>();
                            params.put("q", attr);
                            params.put("appid", API_KEY);
                            params.put("units", "metric");
                            try {
                                String weatherStr = httpClient.get(URL, params, null);
                                res.append(parseWeather(weatherStr, attr));
                            } catch (HttpClientException e) {
                                res
                                        .append(BotStrings.GET_WEATHER_EXC_MESSAGE)
                                        .append(attr.toUpperCase()).append("\n");
                            }
                        }
                        return res.toString();
                    }
                };

                task.setOnSucceeded(workerStateEvent -> {
                    app.getMessages().appendText(task.getValue());
                });

                new Thread(task).start();
            }
        }
    }

    private String parseWeather(String jsonString, String city) {
        JsonObject weather = JsonParser.parseString(jsonString).getAsJsonObject();
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
        return "--- WEATHER IN " + city.toUpperCase() + "\n" +
                "TEMPERATURE: " + temp + " C\n" +
                "HUMIDITY: " + humidity + "%\n" +
                "PRECIPITATION: " + precipitation + "\n";
    }

    @Override
    public String help() {
        return "WEATHER [cities]\n" +
                "Shows the weather for the specified city.\n";
    }
}
