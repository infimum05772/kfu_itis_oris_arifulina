package ru.kpfu.itis.arifulina.fx.chat.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.arifulina.fx.chat.model.UserConfig;

public class UserConfigView extends BaseView {

    private AnchorPane pane;
    private VBox box;
    private TextField host;
    private TextField port;
    private TextField username;
    private Button start;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == start) {
                UserConfig userConfig = new UserConfig(
                        username.getText(),
                        host.getText(),
                        Integer.parseInt(port.getText())
                );

                getChatApp().setUserConfig(userConfig);

                getChatApp().setView(getChatApp().getChatView());

                getChatApp().startChat();
            }
        }
    };

    @Override
    public Parent getView() {
        if (pane == null) {
            createView();
        }
        return pane;
    }
    @Override
    public void createView() {
        pane = new AnchorPane();
        box = new VBox(10);

        Label usernameLabel = new Label("username");
        username = new TextField();
        Label hostLabel = new Label("host");
        host = new TextField();
        host.setText("127.0.0.1");
        Label portLabel = new Label("port");
        port = new TextField();
        port.setText("5555");
        start = new Button("start chat");

        start.setOnAction(eventHandler);

        box.getChildren().addAll(username, host, port, start);
        pane.getChildren().addAll(box);
    }
}
