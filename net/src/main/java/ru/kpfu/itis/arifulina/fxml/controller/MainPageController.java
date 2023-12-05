package ru.kpfu.itis.arifulina.fxml.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import ru.kpfu.itis.arifulina.fxml.app.AbstractApp;
import ru.kpfu.itis.arifulina.fxml.command.CommandInterface;
import ru.kpfu.itis.arifulina.fxml.command.impl.*;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fxml.exception.CommandManagerException;
import ru.kpfu.itis.arifulina.fxml.exception.InvalidInputException;

import java.util.Arrays;

public class MainPageController extends AbstractApp {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField message;
    @FXML
    private TextArea messages;
    @FXML
    private Button submit;
    private boolean isActive;

    public MainPageController(){
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public TextArea getMessages() {
        return messages;
    }

    public AnchorPane getMainPane(){
        return mainPane;
    }

    @FXML
    private void initialize(){
        submit.setText("submit");
        submit.setStyle("-fx-background-color: #b0e3b7");

        submit.setOnAction(actionEvent -> startCommand());

        message.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                startCommand();
            }
        });

        initCommandManager();
    }
    private void initCommandManager(){
        try {
            commandManager.registerCommand("weather", new GetWeatherCommand());
            commandManager.registerCommand("currency", new GetCurrencyCommand());
            commandManager.registerCommand("chat", new StartChatCommand());
            commandManager.registerCommand("snake", new StartSnakeCommand());
            commandManager.registerCommand("help", new HelpCommand());
            commandManager.registerCommand("start", new StartCommand());
            commandManager.registerCommand("end", new EndCommand());
        }
        catch (CommandManagerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void startCommand() {
        String messageText = message.getText();

        try {
            String[] fields = parseInput(messageText);
            if (fields == null){
                return;
            }
            CommandInterface command = commandManager.getCommand(fields[0]);
            command.execute(Arrays.copyOfRange(fields, 1, fields.length));
        }
        catch (CommandExecutionException | InvalidInputException ex) {
            messages.appendText(ex.getMessage());
        }

        message.setText("");
    }

    public String[] parseInput(String userInput){
        userInput = userInput.trim();
        if (userInput.isEmpty()){
            return null;
        }
        return userInput.split("\\s+");
    }
}
