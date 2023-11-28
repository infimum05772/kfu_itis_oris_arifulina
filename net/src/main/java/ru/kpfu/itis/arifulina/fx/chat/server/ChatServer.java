package ru.kpfu.itis.arifulina.fx.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public void start() {
        try {
            serverSocket = new ServerSocket(5555);
            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));

                Client client = new Client(input, output, this, clientSocket);
                clients.add(client);

                new Thread(client).start();

                if (!messages.isEmpty()) {
                    try {
                        client.getOut().write(String.join("", messages));
                        client.getOut().flush();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message, Client client) {
        for (Client c : clients) {
            if (c.equals(client)) {
                continue;
            }
            try {
                c.getOut().write(message);
                c.getOut().flush();
            } catch (IOException e) {
                c.stop();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }

    static class Client implements Runnable {
        private BufferedReader in;
        private BufferedWriter out;
        private ChatServer chatServer;
        private Socket clientSocket;
        private boolean alive = true;

        public Client(BufferedReader in, BufferedWriter out, ChatServer chatServer, Socket clientSocket) {
            this.in = in;
            this.out = out;
            this.chatServer = chatServer;
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                while (alive) {
                    String message = in.readLine() + "\n";
                    chatServer.sendMessage(message, this);
                    chatServer.messages.add(message);
                }
            } catch (IOException e) {
                this.stop();
            }
        }

        public BufferedReader getIn() {
            return in;
        }

        public void setIn(BufferedReader in) {
            this.in = in;
        }

        public BufferedWriter getOut() {
            return out;
        }

        public void setOut(BufferedWriter out) {
            this.out = out;
        }

        public void stop() {
            try {
                in.close();
                out.close();
                clientSocket.close();
                chatServer.clients.remove(this);
                alive = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
