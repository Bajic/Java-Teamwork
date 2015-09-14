package models;

import displays.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Train {
    private BufferedImage trainImage;
    private BufferedImage[] images;
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private String direction;
    private ColorType color;

    private boolean visible; //TODO: make Enum direction

    private Rectangle boundingBox;

    public Train(ColorType color) {
        this.x = 140;
        this.y = 540;
        this.width = 10;
        this.height = 10;
        this.speed = 4;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.direction = "right";
        visible = true;
        this.color = color;
        this.images = getImages(color);
    }

    public ColorType getColor (){
        return this.color;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
                this.trainImage = images[3];
                break;
            case "down":
                this.y += this.speed;
                this.trainImage = images[0];
                break;
            case "left":
                this.x -= this.speed;
                this.trainImage = images[1];
                break;
            case "right":
                this.x += this.speed;
                this.trainImage = images[2];
                break;
        }
    }

    public void draw(Graphics graphics) {
        if (visible) {
            graphics.drawImage(this.trainImage, this.x, this.y, null);
        }
    }


    private BufferedImage[] getImages(ColorType color) {
        switch (color){
            case black:
                return Assets.blackTrain;
            case white:
                return Assets.whiteTrain;
            case red:
                return Assets.redTrain;
            case blackGreen:
                return Assets.blackGreenTrain;
            case blue:
                return Assets.blueTrain;
            case yellow:
                return Assets.yellowTrain;
            case green:
                return Assets.greenTrain;
            case purple:
                return Assets.purpleTrain;
        }
        return null;
    }
}
