package ru.kpfu.itis.arifulina.fx.snake.model;

import javafx.scene.paint.Color;

public class Apple extends GameEntity{
    public static final int DURATION_OF_POISONOUS_SHOWING = 5000;
    private boolean isPoisonous;
    public Apple(int x, int y, int size, boolean isPoisonous) {
        super(x, y, size);
        this.isPoisonous = isPoisonous;
        recolor();
    }

    private void recolor() {
        if (isPoisonous) {
            setColor(Color.GREEN);
        } else {
            setColor(Color.BROWN);
        }
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
        recolor();
    }
}
