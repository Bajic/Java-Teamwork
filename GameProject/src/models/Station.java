package models;

import enums.ColorType;

import java.awt.*;

public class Station {
    private int x;
    private int y;
    private ColorType color;

    private Rectangle boundingBox;

    public Station(int x, int y, ColorType color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.boundingBox = new Rectangle(this.x, this.y, 100, 100);
    }

    public ColorType getColor() {
        return this.color;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
