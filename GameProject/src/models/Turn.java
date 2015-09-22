package models;

import java.awt.*;

public class Turn {

    private int width ;
    private int height;
    private int x;
    private int y;

    private Directions direction;

    private Rectangle boundingBox;

    public Turn(int x, int y,int width, int height, Directions direction) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.x = x;
        this.direction = direction;
        this.boundingBox = new Rectangle(x, y, width, height);
    }

    public Directions getDirection() {
        return direction;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
