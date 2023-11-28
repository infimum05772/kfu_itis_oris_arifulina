package ru.kpfu.itis.arifulina.fx.chat.view;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class ChatView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane;

    private final EventHandler<KeyEvent> onKeyEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                String username = getChatApp().getUserConfig().getUsername();
                String message = input.getText() + "\n";

                //send
                getChatApp().getClient().sendMessage(username + ": " + message);

                conversation.appendText("you: " + message);

                input.clear();
                keyEvent.consume();
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

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setTopAnchor(conversation, 60.0);

        input.addEventHandler(KEY_PRESSED, onKeyEvent);
        pane.getChildren().addAll(input, conversation);
    }

    public void appendMessage(String message){
        if (message != null){
            conversation.appendText(message);
        }
    }

}
