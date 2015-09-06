package models;

import displays.ImageLoader;

import java.awt.*;

public class Train {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private String direction;   //TODO: make Enum direction

    private Rectangle boundingBox;

    public Train() {
        this.x = 500;
        this.y = 100;
        this.width = 95;
        this.height = 130;
        this.speed = 2;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.direction = "left";
    }

    public boolean Intersects(Rectangle otherObject) {
        if (this.boundingBox.contains(otherObject) || otherObject.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }

    public void update() {
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
        switch (direction) {
            case "up":
                this.y -= this.speed;
                break;
            case "down":
                this.y += this.speed;
                break;
            case "left":
                this.x -= this.speed;
                break;
            case "right":
                this.x += this.speed;
                break;
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(ImageLoader.load("/images/Train.gif"), this.x, this.y, null);
    }
}
