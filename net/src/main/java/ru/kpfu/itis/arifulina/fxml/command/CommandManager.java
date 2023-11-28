package ru.kpfu.itis.arifulina.fxml.command;

import ru.kpfu.itis.arifulina.fxml.app.AbstractApp;
import ru.kpfu.itis.arifulina.fxml.exception.CommandManagerException;
import ru.kpfu.itis.arifulina.fxml.exception.InvalidInputException;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private AbstractApp app;
    private List<CommandInterface> commands;
    private List<String> commandNames;

    public CommandManager(AbstractApp app) {
        this.app = app;
        this.commands = new ArrayList<>();
        this.commandNames = new ArrayList<>();
    }

    public List<CommandInterface> getCommands() {
        return commands;
    }

    public void registerCommand(String commandName, CommandInterface command)
            throws CommandManagerException {
        for (String name:commandNames) {
            if(commandName.equals(name)) {
                throw new CommandManagerException("such command already exists");
            }
        }
        command.init(commandName, app);
        commands.add(command);
        commandNames.add(commandName);
    }

    public CommandInterface getCommand(String commandName)
            throws InvalidInputException {
        int index = commandNames.indexOf(commandName);
        if(index == -1){
            throw new InvalidInputException(BotStrings.INVALID_INPUT_MESSAGE);
        }
        return commands.get(index);
    }
}
