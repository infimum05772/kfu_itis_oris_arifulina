package ru.kpfu.itis.arifulina.fxml.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.arifulina.fxml.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PageController {
    @FXML
    private TextField username;
    @FXML
    private Label label;
    @FXML
    private Button search;
    @FXML
    private VBox users;
    @FXML
    private TableView tableViewUsers;
    private ObservableList<User> usersList = FXCollections.observableArrayList();
    private ObservableList<User> resultList = FXCollections.observableArrayList();

    public PageController(){
        usersList.add(new User("Ivan", 50));
        usersList.add(new User("NeIvan", 60));
        usersList.add(new User("PochtiIvan", 70));
        usersList.add(new User("Ar", 100));
    }

    @FXML
    private void initialize() {
        search.setText("Search");
        search.setStyle("-fx-background-color: #b0e3b7");

        search.setOnAction(actionEvent -> searchUsers());

        username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                searchUsers();
            }
        });

        username.textProperty().addListener((observableValue, oldValue, newValue) -> {
            label.setText(newValue);
        });

        initTable();
    }

    private void searchUsers() {
        String searchInput = username.getText();

        Task<ObservableList<User>> task = new Task<>() {
            @Override
            protected ObservableList<User> call() {
                return FXCollections.observableArrayList(usersList.stream()
                        .filter(u -> u.getUsername().toLowerCase().contains(searchInput.toLowerCase()))
                        .collect(Collectors.toList()));
            }
        };

        task.setOnSucceeded(workerStateEvent -> {
            resultList = task.getValue();
            tableViewUsers.setItems(resultList);
        });

        new Thread(task).start();
    }

    private void initTable() {
        tableViewUsers = new TableView<>(usersList);
        tableViewUsers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn username = new TableColumn<>("USERNAME");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn score = new TableColumn<>("SCORE");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableViewUsers.getColumns().addAll(username, score);

        users.getChildren().add(tableViewUsers);
    }
}
