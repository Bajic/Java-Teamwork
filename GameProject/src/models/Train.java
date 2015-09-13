package models;

import displays.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Train {
    private BufferedImage trainImage;
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private String direction;   //TODO: make Enum direction

    private Rectangle boundingBox;

    public Train() {
        this.x = 140;
        this.y = 540;
        this.width = 10;
        this.height = 10;
        this.speed = 4;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.direction = "right";
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
                this.trainImage = Assets.trainUp;
                break;
            case "down":
                this.y += this.speed;
                this.trainImage = Assets.trainDown;
                break;
            case "left":
                this.x -= this.speed;
                this.trainImage = Assets.trainLeft;
                break;
            case "right":
                this.x += this.speed;
                this.trainImage = Assets.trainRight;
                break;
        }
    }

    public void draw(Graphics graphics)
    {
        graphics.drawImage(this.trainImage, this.x, this.y, null);
    }
}
