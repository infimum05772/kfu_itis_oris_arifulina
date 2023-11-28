package ru.kpfu.itis.arifulina.fxml.command.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.concurrent.Task;
import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;
import ru.kpfu.itis.arifulina.http_client.HTTPClientImp;
import ru.kpfu.itis.arifulina.http_client.HttpClientException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class GetCurrencyCommand extends AbstractCommand {
    public static final String URL = "https://api.currencyapi.com/v3/latest";
    public static final String API_KEY = "cur_live_oe5zyQLZ8NCBKV5fLhkwqSYdmegXezsAQXn7djum";
    public static final HTTPClientImp httpClient = new HTTPClientImp();

    @Override
    public void execute(String[] attributes) throws CommandExecutionException {
        if (!app.isActive()) {
            throw new CommandExecutionException(BotStrings.MESSAGE_BEFORE_START);
        } else {
            if (attributes.length != 2) {
                throw new CommandExecutionException(BotStrings.INVALID_ARGS_CURRENCY_MESSAGE);
            } else {
                Task<String> task = new Task<>() {
                    @Override
                    protected String call() throws Exception {
                        Map<String, String> params = new HashMap<>();
                        params.put("apikey", API_KEY);
                        params.put("base_currency", attributes[0].toUpperCase());
                        params.put("currencies", attributes[1].toUpperCase());
                        try {
                            String currencyStr = httpClient.get(URL, params, null);
                            return parseCurrency(currencyStr, attributes[1].toUpperCase());
                        } catch (HttpClientException e) {
                            return BotStrings.GET_CURRENCY_EXC_MESSAGE;
                        }
                    }
                };

                task.setOnSucceeded(workerStateEvent -> {
                    app.getMessages().appendText(task.getValue());
                });

                new Thread(task).start();
            }
        }
    }

    private String parseCurrency(String currencyStr, String currency) throws ParseException {
        JsonObject resultJson = JsonParser.parseString(currencyStr).getAsJsonObject();
        JsonObject currencyJson = resultJson.get("data").getAsJsonObject().get(currency).getAsJsonObject();
        return currencyJson.get("value") + " " + currency + "\n";
    }

    @Override
    public String help() {
        return "CURRENCY [base currency] [currency]\n" +
                "Shows the exchange rate for the specified currency.\n" +
                "[base currency] - start currency\n" +
                "[currency] - new currency\n";
    }
}
