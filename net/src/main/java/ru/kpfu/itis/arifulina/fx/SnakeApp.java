package ru.kpfu.itis.arifulina.fx;

import java.time.LocalTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.kpfu.itis.arifulina.fx.snake.model.Apple;
import ru.kpfu.itis.arifulina.fx.snake.model.Direction;
import ru.kpfu.itis.arifulina.fx.snake.model.Snake;

public class SnakeApp extends Application {
    private static final int BOARD_SIZE = 500;
    private static final int RECT_SIZE = 25;
    private static final int CELLS_AMOUNT = 20;
    private static final double SPEED_INCREASING_FACTOR = 0.8;
    private static final int SPEED_INCREASING_FREQUENCY = 30000;
    private static final double POISON_APPEARING_PROBABILITY = 0.3;
    private Label timerLabel;
    private Label scoreLabel;
    private int frameRate = 200;
    private int score = 0;
    private Snake snake;
    private Apple apple;
    private final Random r = new Random();
    private final Pane root = new Pane();
    private boolean isDone = false;
    LocalTime time;


    public void start(Stage primaryStage) {
        initGame();
        Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);
        scene.setOnKeyPressed(event -> {
            onDirectionEntered(event.getCode());
        });
        setSpeedIncreasing();
        initLabels();
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(BOARD_SIZE);
        primaryStage.setMinWidth(BOARD_SIZE);
        primaryStage.setMaxHeight(BOARD_SIZE);
        primaryStage.setMaxWidth(BOARD_SIZE);
        primaryStage.setOnCloseRequest(event -> isDone = true);
        primaryStage.show();

    }

    private void onDirectionEntered(KeyCode k) {
        switch (k) {
            case RIGHT, D -> snake.setDirection(Direction.RIGHT);
            case LEFT, A -> snake.setDirection(Direction.LEFT);
            case DOWN, S -> snake.setDirection(Direction.DOWN);
            case UP, W -> snake.setDirection(Direction.UP);
        }
    }

    private void initLabels() {
        time = LocalTime.of(0, 0, 0);
        scoreLabel = new Label("score: " + score);
        timerLabel = new Label("00:00:00");
        timerLabel.setTranslateY(15);
        root.getChildren().addAll(scoreLabel, timerLabel);
        TimerTask updateTimerLabel = new TimerTask() {
            @Override
            public void run() {
                time = time.plusSeconds(1);
                Platform.runLater(() ->
                        timerLabel.setText(time.toString()));
            }
        };
        Timer timerUpdateTimerLabel = new Timer();
        timerUpdateTimerLabel.schedule(updateTimerLabel, 1000, 1000);
    }

    private void initGame() {
        snake = new Snake(
                0, 0,
                RECT_SIZE
        );
        apple = new Apple(
                r.nextInt(CELLS_AMOUNT) * RECT_SIZE,
                r.nextInt(CELLS_AMOUNT) * RECT_SIZE,
                RECT_SIZE,
                Math.random() <= POISON_APPEARING_PROBABILITY
        );
        root.getChildren().add(apple.getRect());
        root.getChildren().add(snake.getRect());
        if (apple.isPoisonous()) {
            showPoisonousApple();
        }
        Thread game;
        game = new Thread(() -> {
            while (!isDone) {
                move();
                try {
                    Thread.sleep(frameRate);
                } catch (InterruptedException ex) {
                }
            }
            Platform.exit();
        });
        game.start();
    }

    private void setSpeedIncreasing() {
        TimerTask increaseSpeed = new TimerTask() {
            @Override
            public void run() {
                frameRate = (int) Math.round(frameRate * SPEED_INCREASING_FACTOR);
            }
        };
        Timer timer = new Timer();
        timer.schedule(increaseSpeed, 0, SPEED_INCREASING_FREQUENCY);
    }

    private void showPoisonousApple() {
        TimerTask increaseSpeed = new TimerTask() {
            @Override
            public void run() {
                generateApple();
            }
        };
        Timer timer = new Timer();
        timer.schedule(increaseSpeed, Apple.DURATION_OF_POISONOUS_SHOWING);
    }

    private void move() {
        switch (snake.getDirection()) {
            case DOWN:
                if (snake.getY() + RECT_SIZE <= BOARD_SIZE - RECT_SIZE)
                    snake.setY(snake.getY() + RECT_SIZE);
                else isDone = true;
                break;
            case LEFT:
                if (snake.getX() - RECT_SIZE >= 0)
                    snake.setX(snake.getX() - RECT_SIZE);
                else isDone = true;
                break;
            case UP:
                if (snake.getY() - RECT_SIZE >= 0)
                    snake.setY(snake.getY() - RECT_SIZE);
                else isDone = true;
                break;
            case RIGHT:
                if (snake.getX() + RECT_SIZE <= BOARD_SIZE - RECT_SIZE)
                    snake.setX(snake.getX() + RECT_SIZE);
                else isDone = true;
                break;
        }
        if (snake.getRect().getBoundsInParent().intersects(apple.getRect().getBoundsInParent())) {
            if (!apple.isPoisonous()) {
                generateApple();
                snake.grow();
                score++;
                Platform.runLater(() -> scoreLabel.setText("score: " + score));
            } else {
                isDone = true;
            }
        }
    }

    private void generateApple() {
        apple.setLocation(r.nextInt(CELLS_AMOUNT) * RECT_SIZE, r.nextInt(CELLS_AMOUNT) * RECT_SIZE);
        apple.setPoisonous(Math.random() <= POISON_APPEARING_PROBABILITY);
        if (apple.isPoisonous()) {
            showPoisonousApple();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
