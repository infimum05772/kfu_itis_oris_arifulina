package ru.kpfu.itis.arifulina.tcp;

import java.util.Scanner;

public class ClientTest {

    public static void main(String[] args) {
        GreetingClient greetingClient = new GreetingClient();
        greetingClient.start("127.0.0.1", 5555);

        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null ) {
            System.out.println(greetingClient.send(line));
        }

        greetingClient.stop();
    }
}
