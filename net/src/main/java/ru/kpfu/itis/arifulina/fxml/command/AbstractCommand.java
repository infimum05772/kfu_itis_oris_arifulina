package ru.kpfu.itis.arifulina.fxml.command;

import ru.kpfu.itis.arifulina.fxml.app.AbstractApp;
import ru.kpfu.itis.arifulina.fxml.controller.MainPageController;

public abstract class AbstractCommand implements CommandInterface{
    protected String name;
    protected MainPageController app;
    public void init(String name, AbstractApp app) {
        this.name = name;
        this.app = (MainPageController) app;
    }
}
