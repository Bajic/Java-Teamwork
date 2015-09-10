package models;

import java.awt.*;

public class Turn {

    private final int width = 50;
    private final int height = 50;
    private int x;
    private int y;

    private String direction;

    private Rectangle boundingBox;

    public Turn(int x, int y, String direction) {
        this.y = y;
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

    //public void draw(Graphics graphics) {
        //graphics.drawRect(x,y,width,height);
    //}
}
