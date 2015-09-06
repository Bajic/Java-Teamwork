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
        this.x = 400;
        this.y = 100;
        this.width = 100;
        this.height = 100;
        this.speed = 5;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.direction = "left";
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean intersects(Rectangle otherObject) {
        return this.boundingBox.x + this.boundingBox.width >= otherObject.x &&
                this.boundingBox.y + this.boundingBox.height >= otherObject.y &&
                this.boundingBox.y <= otherObject.y + otherObject.height &&
                this.boundingBox.x <= otherObject.x + otherObject.width;
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
