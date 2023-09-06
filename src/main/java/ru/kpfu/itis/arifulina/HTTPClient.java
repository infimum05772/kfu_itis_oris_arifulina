package ru.kpfu.itis.arifulina;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPClient implements HttpClientInterface{
    public static final String TEST_URL = "https://gorest.co.in/public/v2/users";
    public static final String ACCESS_TOKEN = "79837d71c309d7ea65bd1c53e820288cbad06a8427a95cf0056617f9d39fa225";
    public static void main(String[] args) {
        //tests are written specifically for the selected url

        HTTPClient httpClient = new HTTPClient();
        try{
            //get
            HashMap<String, String> params_get = new HashMap<>();
            String getResult = httpClient.get(TEST_URL, params_get, ACCESS_TOKEN);
            System.out.println(getResult);

            //the first user found is deleted
            HashMap<String, String> params_delete = new HashMap<>();
            Pattern pattern = Pattern.compile("\\{[^{}]*}");
            Matcher matcher = pattern.matcher(getResult);
            matcher.find();
            JsonObject user = JsonParser.parseString(matcher.group()).getAsJsonObject();
            int userId = user.get("id").getAsInt();
            System.out.println(httpClient.delete(TEST_URL + "/" + userId, params_delete, ACCESS_TOKEN));

            //user just deleted is posted
            HashMap<String,String> params_post = new HashMap<>();
            params_post.put("name", user.get("name").getAsString());
            params_post.put("email", user.get("email").getAsString());
            params_post.put("gender", user.get("gender").getAsString());
            params_post.put("status", user.get("status").getAsString());
            System.out.println(httpClient.post(TEST_URL, params_post, ACCESS_TOKEN));

            //the second user found is put
            matcher.find();
            JsonObject user2 = JsonParser.parseString(matcher.group()).getAsJsonObject();
            int userId2 = user2.get("id").getAsInt();
            HashMap<String,String> params_put = new HashMap<>();
            params_post.put("name", user2.get("name").getAsString());
            params_post.put("email", user2.get("email").getAsString());
            params_post.put("status", user2.get("status").getAsString());
            System.out.println(httpClient.put(TEST_URL + "/" + userId2, params_put, ACCESS_TOKEN));
        } catch (HttpClientException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String get(String url, Map<String, String> params, String token) throws HttpClientException {
        try {
            if (params != null && params.size() != 0){
                StringBuilder urlParams = new StringBuilder();
                params.forEach((key, value) -> urlParams.append(key).append("=").append(value).append("&"));
                url = url + "?" + urlParams.substring(0, urlParams.length()-1);
            }

            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            if (token != null){
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            String result = getResponse(connection);

            connection.disconnect();
            return result;
        } catch (IOException e){
            throw new HttpClientException(e.getMessage());
        }
    }

    @Override
    public String post(String url, Map<String, String> params, String token) throws HttpClientException {
        return makeRequestWithBody(url, params, "POST", token);
    }

    @Override
    public String put(String url, Map<String, String> params, String token) throws HttpClientException {
        return makeRequestWithBody(url, params, "PUT", token);
    }

    @Override
    public String delete(String url, Map<String, String> params, String token) throws HttpClientException {
        return makeRequestWithBody(url, params, "DELETE", token);
    }
    private String makeRequestWithBody(String url, Map<String,String> params, String methodName, String token) throws HttpClientException {
        try {
            URL putUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) putUrl.openConnection();

            connection.setRequestMethod(methodName);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            if (token != null){
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            connection.setDoOutput(true);

            Gson gson = new Gson();
            String jsonInput = gson.toJson(params);

            try (OutputStream out = connection.getOutputStream()){
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                out.write(input, 0, input.length);
                out.flush();
            }

            String result = getResponse(connection);

            connection.disconnect();
            return result;
        }catch (IOException e){
            throw new HttpClientException(e.getMessage());
        }
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        int responseCode = connection.getResponseCode();
        content.append("Response code: ").append(responseCode).append("\n");
        if (responseCode >= 200 && responseCode <= 299){
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String input;
                while ((input = reader.readLine()) != null){
                    content.append(input);
                }
            }
        } else {
            content.append("Request failed for url: ").append(connection.getURL());
        }
        return content.toString();
    }
}
