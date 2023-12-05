package ru.kpfu.itis.arifulina.fxml.command.impl;

import javafx.stage.Stage;
import ru.kpfu.itis.arifulina.fx.SnakeApp;
import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;

public class StartSnakeCommand extends AbstractCommand {
    @Override
    public void execute(String[] attributes) throws CommandExecutionException {
        if (!app.isActive()) {
            throw new CommandExecutionException(BotStrings.MESSAGE_BEFORE_START);
        } else {
            SnakeApp snake = new SnakeApp();
            try {
                snake.start(((Stage) app.getMainPane().getScene().getWindow()));
            } catch (Exception e){
                throw new CommandExecutionException(BotStrings.STARTING_SNAKE_EXC_MESSAGE);
            }
        }
    }

    @Override
    public String help() {
        return "SNAKE\n" +
                "Starts weird snake game.\n";
    }
}
