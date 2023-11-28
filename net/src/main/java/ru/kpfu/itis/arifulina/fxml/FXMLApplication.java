package ru.kpfu.itis.arifulina.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(FXMLApplication.class.getResource("/page.fxml"));

        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        //scene.getStylesheets().add("/page.css");

        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}
