package ru.kpfu.itis.arifulina.fx.snake.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEntity {
    protected int[] location;
    protected Rectangle rect;
    protected Color color;

    public GameEntity(int x, int y, int size) {
        location = new int[]{x, y};
        rect = new Rectangle(size, size);
        move();
    }

    private void move() {
        rect.setTranslateX(location[0]);
        rect.setTranslateY(location[1]);
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
        move();
    }

    public void setX(int x) {
        location[0] = x;
        move();
    }

    public void setY(int y) {
        location[1] = y;
        move();
    }
    public int getX() {
        return location[0];
    }
    public int getY() {
        return location[1];
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
        rect.setFill(color);
    }
}
