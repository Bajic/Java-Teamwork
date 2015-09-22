package models;

import java.awt.*;

public class Turn {

    private int width ;
    private int height;
    private int x;
    private int y;

    private String direction;

    private Rectangle boundingBox;

    public Turn(int x, int y,int width, int height, String direction) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.x = x;
        this.direction = direction;
        this.boundingBox = new Rectangle(x, y, width, height);
    }

    public String getDirection() {
        return direction;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
