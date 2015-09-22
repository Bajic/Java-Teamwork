package models;

import displays.Assets;
import enums.ColorType;
import enums.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Train {
    private BufferedImage trainImage;
    private BufferedImage[] images;
    private int x;
    private int y;
    private double speed;
    private int width;
    private int height;
    private Directions direction;
    private ColorType color;

    private boolean visible; //TODO: make Enum direction

    private Rectangle boundingBox;

    public Train(ColorType color, double speed) {
        this.x = 140;
        this.y = 540;
        this.width = 10;
        this.height = 10;
        this.speed = speed;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.direction = Directions.RIGHT;
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

    public boolean getVisible() {
        return this.visible;
    }

    public void setDirection(Directions direction) {
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
            case UP:
                this.y -= this.speed;
                this.trainImage = images[3];
                break;
            case DOWN:
                this.y += this.speed;
                this.trainImage = images[0];
                break;
            case LEFT:
                this.x -= this.speed;
                this.trainImage = images[1];
                break;
            case RIGHT:
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
            case BLACK:
                return Assets.blackTrain;
            case WHITE:
                return Assets.whiteTrain;
            case RED:
                return Assets.redTrain;
            case BLACK_GREEN:
                return Assets.blackGreenTrain;
            case BLUE:
                return Assets.blueTrain;
            case YELLOW:
                return Assets.yellowTrain;
            case GREEN:
                return Assets.greenTrain;
            case PURPLE:
                return Assets.purpleTrain;
        }
        return null;
    }
}
