package ru.kpfu.itis.arifulina.fxml.command.impl;

import ru.kpfu.itis.arifulina.fxml.command.AbstractCommand;
import ru.kpfu.itis.arifulina.fxml.utils.BotStrings;

public class StartCommand extends AbstractCommand {
    @Override
    public void execute(String[] attributes) {
        if (!app.isActive()){
            app.setActive(true);
            app.getMessages().appendText(BotStrings.MESSAGE_AFTER_START);
        } else {
            app.getMessages().appendText(BotStrings.MESSAGE_REPEATED_START);
        }
    }

    @Override
    public String help() {
        return "START\n" +
                "Starts your help bot.\n";
    }
}
