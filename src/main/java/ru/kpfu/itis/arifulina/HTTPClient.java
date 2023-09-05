package ru.kpfu.itis.arifulina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class HTTPClient  {
    public static void main(String[] args) {
        //get
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null){
                    content.append(input);
                }
                //System.out.println(content);
            }
            connection.disconnect();
        } catch (IOException e){
            throw new RuntimeException();
        }

        //post
        try {
            URL postUrl = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConnection =  (HttpURLConnection) postUrl.openConnection();

            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer 79837d71c309d7ea65bd1c53e820288cbad06a8427a95cf0056617f9d39fa225");
            postConnection.setConnectTimeout(5000);
            postConnection.setReadTimeout(5000);

            postConnection.setDoOutput(true);

            String jsoninput = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"tenali.ramakrishna@15ce.com\", \"status\":\"active\"}";

            try (OutputStream out = postConnection.getOutputStream()){
                byte[] input = jsoninput.getBytes(StandardCharsets.UTF_8);
                out.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))){
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null){
                    content.append(input);
                }
                System.out.println(content);
            }
            postConnection.disconnect();
        }catch (IOException e){
            throw new RuntimeException();
        }


    }

}
