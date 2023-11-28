package ru.kpfu.itis.arifulina.fxml.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleStringProperty username;
    private SimpleIntegerProperty score;

    public User(String username, Integer score) {
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
