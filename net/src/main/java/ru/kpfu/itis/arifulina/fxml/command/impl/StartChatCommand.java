package ru.kpfu.itis.arifulina.fxml.command.impl;

import javafx.stage.Stage;
import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fx.chat.ChatApp;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;

public class StartChatCommand extends AbstractCommand {
    @Override
    public void execute(String[] attributes) throws CommandExecutionException {
        if (!app.isActive()) {
            throw new CommandExecutionException(BotStrings.MESSAGE_BEFORE_START);
        } else {
            ChatApp chatApp = new ChatApp();
            try {
                chatApp.start(((Stage) app.getMainPane().getScene().getWindow()));
            } catch (Exception e){
                throw new CommandExecutionException(BotStrings.STARTING_CHAT_EXC_MESSAGE);
            }
        }
    }

    @Override
    public String help() {
        return "CHAT\n" +
                "Starts online chat.\n";
    }
}
