package ru.kpfu.itis.arifulina.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BotFXMLApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(BotFXMLApplication.class.getResource("/main_page.fxml"));

        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        stage.setTitle("HelpBot");
        stage.setScene(scene);
        stage.show();
    }
}
