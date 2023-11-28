package ru.kpfu.itis.arifulina.fx.chat.view;

import javafx.scene.Parent;
import ru.kpfu.itis.arifulina.fx.chat.ChatApp;

public abstract class BaseView {

    public static ChatApp chatApp;

    public static ChatApp getChatApp(){
        if (chatApp != null) {
            return chatApp;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setChatApp(ChatApp chatApp){
        BaseView.chatApp = chatApp;
    }

    public abstract Parent getView();
    public abstract void createView();
}
