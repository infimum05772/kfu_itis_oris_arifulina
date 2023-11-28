package ru.kpfu.itis.arifulina.fx.chat.model;

public class UserConfig {
    private String username;
    private int port;
    private String host;

    public UserConfig(String username, String host, int port) {
        this.username = username;
        this.host = host;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
