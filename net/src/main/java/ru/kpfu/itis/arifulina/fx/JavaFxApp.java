package ru.kpfu.itis.arifulina.fx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.Optional;

public class JavaFxApp extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Line line = new Line();
        line.setStartX(50);
        line.setStartY(50);
        line.setEndX(150);
        line.setEndY(150);

        Text text = new Text();
        text.setFont(new Font(40));
        text.setX(100);
        text.setY(200);
        text.setText("text");

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Form");
        GridPane pane = new GridPane();

        ButtonType connect = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
        TextField username = new TextField();
        username.setPromptText("username");
        pane.add(username, 1, 0);

        dialog.getDialogPane().getButtonTypes().addAll(connect);
        dialog.getDialogPane().setContent(pane);

        dialog.setResultConverter(button -> {
            if (button.equals(connect)) {
                return username.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(System.out::println);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("alert");
        alert.setContentText("блять");

        Button click = new Button("click me");
        click.setOnAction(actionEvent -> alert.show());

        Group root = new Group();

        ObservableList children = root.getChildren();
        children.addAll(line, text, click);

        Scene main = new Scene(root, 600, 600);
        stage.setTitle("pizdoblyadstvo");
        stage.setScene(main);
        stage.show();

        KeyCombination kc = new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN);

        main.setOnKeyPressed(key -> {
            if (kc.match(key)) {
                System.out.println("извините за мат");
            }
            switch (key.getCode()){
                case A -> alert.show();
                case Q -> stage.close();
            }
        });
    }
}
