package ru.kpfu.itis.arifulina.fx.snake.model;

import javafx.scene.paint.Color;

public class Snake extends GameEntity {
    private static final double GROW_FACTOR = 1.05;
    private Direction direction;
    public Snake(int x, int y, int size) {
        super(x, y, size);
        setColor(Color.GRAY);
        direction = Direction.RIGHT;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow(){
        rect.setWidth(rect.getWidth() * GROW_FACTOR);
        rect.setHeight(rect.getHeight() * GROW_FACTOR);
    }
}
