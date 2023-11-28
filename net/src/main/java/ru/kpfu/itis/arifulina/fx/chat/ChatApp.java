package ru.kpfu.itis.arifulina.fx.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.arifulina.fx.chat.client.ChatClient;
import ru.kpfu.itis.arifulina.fx.chat.model.UserConfig;
import ru.kpfu.itis.arifulina.fx.chat.view.BaseView;
import ru.kpfu.itis.arifulina.fx.chat.view.ChatView;
import ru.kpfu.itis.arifulina.fx.chat.view.UserConfigView;

public class ChatApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    private UserConfig userConfig;
    private UserConfigView configView;
    private ChatView chatView;
    private BorderPane root;
    private ChatClient client;

    @Override
    public void start(Stage stage) throws Exception {
        client = new ChatClient(this);

        stage.setTitle("CHAT");
        stage.setOnCloseRequest(e -> {
            System.exit(0);
            client.getThread().stop();
        });

        BaseView.setChatApp(this);

        configView = new UserConfigView();
        chatView = new ChatView();

        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();

        setView(configView);
    }

    public void appendMessage(String message){
        chatView.appendMessage(message);
    }

    public void startChat() {
        client.start();
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public UserConfigView getConfigView() {
        return configView;
    }

    public void setConfigView(UserConfigView configView) {
        this.configView = configView;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public ChatClient getClient() {
        return client;
    }

    public void setClient(ChatClient client) {
        this.client = client;
    }
}
