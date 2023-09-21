package ru.kpfu.itis.arifulina.net.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClientTest {
    public static final String TEST_URL = "https://gorest.co.in/public/v2/users";
    public static final String ACCESS_TOKEN = "79837d71c309d7ea65bd1c53e820288cbad06a8427a95cf0056617f9d39fa225";
    private HttpClient httpClient;

    @BeforeEach
    public void init() {
        httpClient = new HTTPClientImp();
    }

    @Test
    public void getTest() throws HttpClientException {
        Map<String, String> params = new HashMap<>();
        String response = httpClient.get(TEST_URL, params, ACCESS_TOKEN);
        String responseCode = response.split("\n")[0].split(" ")[2];
        Assertions.assertEquals('2', responseCode.charAt(0));
    }

    @Test
    public void deleteTest() throws HttpClientException {
        Map<String, String> params = new HashMap<>();
        String getResponse = httpClient.get(TEST_URL, params, ACCESS_TOKEN);

        HashMap<String, String> params_delete = new HashMap<>();
        Pattern pattern = Pattern.compile("\\{[^{}]*}");
        Matcher matcher = pattern.matcher(getResponse);
        matcher.find();
        JsonObject user = JsonParser.parseString(matcher.group()).getAsJsonObject();
        int userId = user.get("id").getAsInt();

        String response = httpClient.delete(TEST_URL + "/" + userId, params_delete, ACCESS_TOKEN);
        String responseCode = response.split("\n")[0].split(" ")[2];
        Assertions.assertEquals('2', responseCode.charAt(0));
    }

    @Test
    public void postTest() throws HttpClientException {
        HashMap<String, String> params_post = new HashMap<>();
        params_post.put("name", "Regina Arifulia");
        params_post.put("email", "regina_arifulina@miller.example");
        params_post.put("gender", "female");
        params_post.put("status", "active");

        String response = httpClient.post(TEST_URL, params_post, ACCESS_TOKEN);
        String responseCode = response.split("\n")[0].split(" ")[2];
        Assertions.assertEquals('2', responseCode.charAt(0));
    }

    @Test
    public void putTest() throws HttpClientException {
        Map<String, String> params = new HashMap<>();
        String getResponse = httpClient.get(TEST_URL, params, ACCESS_TOKEN);

        Pattern pattern = Pattern.compile("\\{[^{}]*}");
        Matcher matcher = pattern.matcher(getResponse);
        matcher.find();
        JsonObject user = JsonParser.parseString(matcher.group()).getAsJsonObject();
        int userId = user.get("id").getAsInt();

        HashMap<String, String> params_put = new HashMap<>();
        params_put.put("name", user.get("name").getAsString());
        params_put.put("email", user.get("email").getAsString());
        params_put.put("status", user.get("status").getAsString());

        String response = httpClient.put(TEST_URL + "/" + userId, params_put, ACCESS_TOKEN);
        String responseCode = response.split("\n")[0].split(" ")[2];
        Assertions.assertEquals('2', responseCode.charAt(0));
    }
}
