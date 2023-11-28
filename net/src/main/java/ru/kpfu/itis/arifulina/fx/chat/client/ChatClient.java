package ru.kpfu.itis.arifulina.fx.chat.client;

import ru.kpfu.itis.arifulina.fx.chat.ChatApp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    private final ChatApp chatApp;
    private Socket socket;
    private ClientThread thread;

    public ChatClient(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

    public void sendMessage(String message) {
        try {
            thread.getOut().write(message);
            thread.getOut().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        String host = chatApp.getUserConfig().getHost();
        int port = chatApp.getUserConfig().getPort();

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader input;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter output;
        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        thread = new ClientThread(input, output, this);
        new Thread(thread).start();
    }

    public ClientThread getThread() {
        return thread;
    }

    public class ClientThread implements Runnable {

        private BufferedReader in;
        private BufferedWriter out;
        private ChatClient chatClient;
        private boolean alive = true;

        public ClientThread(BufferedReader in, BufferedWriter out, ChatClient chatClient) {
            this.in = in;
            this.out = out;
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                while (alive) {
                    String message = in.readLine() + "\n";
                    chatClient.chatApp.appendMessage(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
                chatClient.socket.close();
                alive = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}