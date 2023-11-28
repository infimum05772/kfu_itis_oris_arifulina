package ru.kpfu.itis.arifulina.udp;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class GreetingServer extends Thread implements Closeable {

    public static final int PORT = 5556;
    private DatagramSocket socket;
    private boolean alive;
    private byte[] buffer;

    public GreetingServer() {
        try {
            this.socket = new DatagramSocket(PORT);
            this.alive = true;
            this.buffer = new byte[512];
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (alive) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                String newMsg;
                if (message.toLowerCase().contains("хуй")){
                    newMsg = "пошел на хуй";
                } else {
                    newMsg = message + ")))";
                }

                byte[] data = newMsg.getBytes(StandardCharsets.UTF_8);

                socket.send(new DatagramPacket(data, data.length, address, port));

                if ("bye".equalsIgnoreCase(message.trim())){
                    alive = false;
                    close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
