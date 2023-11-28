package ru.kpfu.itis.arifulina.fxml.command.impl;

import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.command.CommandInterface;
import ru.kpfu.itis.arifulina.fxml.exception.CommandExecutionException;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;

public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(String[] attributes) throws CommandExecutionException {
        if (!app.isActive()){
            throw new CommandExecutionException(BotStrings.MESSAGE_BEFORE_START);
        } else {
            for (CommandInterface command: app.getCommandManager().getCommands()){
                app.getMessages().appendText(command.help());
            }
        }
    }

    @Override
    public String help() {
        return "HELP\n" +
                "Displays info about each command.\n";
    }
}
