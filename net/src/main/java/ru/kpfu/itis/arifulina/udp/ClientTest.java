package ru.kpfu.itis.arifulina.udp;

import java.io.IOException;
import java.util.Scanner;

public class ClientTest {

    public static void main(String[] args) {
        new GreetingServer().start();
        try (GreetingClient client = new GreetingClient();){
            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = scanner.nextLine()) != null) {
                System.out.println(client.send(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
