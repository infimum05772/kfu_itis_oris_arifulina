package ru.kpfu.itis.arifulina.fxml.command;

import ru.kpfu.itis.arifulina.fxml.app.AbstractApp;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;

public interface CommandInterface {
    void execute(String[] attributes) throws CommandExecutionException;
    void init(String name, AbstractApp app);
    String help();
}
