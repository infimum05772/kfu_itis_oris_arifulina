package ru.kpfu.itis.arifulina.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);

            clientSocket = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;

            while ((message = in.readLine()) != null){
                if ("hello".equalsIgnoreCase(message.trim())) {
                    out.println("hello from server");
                } else if ("bye".equalsIgnoreCase(message.trim())){
                    out.println("bye");

                    in.close();
                    out.close();
                    clientSocket.close();
                    serverSocket.close();
                    break;
                } else {
                    out.println("wha?");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GreetingServer greetingServer = new GreetingServer();

        greetingServer.start(5555);
    }

}
