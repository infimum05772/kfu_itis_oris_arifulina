module ru.kpfu.itis.arifulina.fxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.kpfu.itis.arifulina.fxml to javafx.fxml;
    exports ru.kpfu.itis.arifulina.fxml;
    exports ru.kpfu.itis.arifulina.fxml.controller;
    opens ru.kpfu.itis.arifulina.fxml.controller to javafx.fxml;
    exports ru.kpfu.itis.arifulina.fxml.model;
    opens ru.kpfu.itis.arifulina.fxml.model to javafx.fxml;
}