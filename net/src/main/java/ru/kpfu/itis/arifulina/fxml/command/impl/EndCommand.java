package ru.kpfu.itis.arifulina.fxml.command.impl;

import javafx.stage.Stage;
import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;

public class EndCommand extends AbstractCommand {
    @Override
    public void execute(String[] attributes) {
        app.setActive(false);
        ((Stage) app.getMainPane().getScene().getWindow()).close();
    }

    @Override
    public String help() {
        return "END\n" +
                "Ends app.\n";
    }
}
