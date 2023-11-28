package ru.kpfu.itis.arifulina.fxml.app;

import ru.kpfu.itis.arifulina.fxml.command.CommandManager;

public abstract class AbstractApp {

    protected CommandManager commandManager;
    public AbstractApp(){
        commandManager = new CommandManager(this);
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }
    public abstract void startCommand();
}
