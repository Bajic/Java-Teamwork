package models;

import displays.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RailroadSwitch {

    private int x;
    private int y;
    private int widthForClick;
    private int width;
    private int heightForClick;
    private int height;
    private Directions direction;
    private Directions oppositeDirection;

    private BufferedImage image;
    private Rectangle boundingBox;
    private Rectangle boundingBoxForClick;

    public RailroadSwitch(int x, int y, int width,int height,int clickX, int clickY, Directions direction, Directions oppositeDirection) {
        this.x = x;
        this.y = y;
        this.widthForClick = 75;
        this.width = width;
        this.heightForClick = 75;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
        this.boundingBoxForClick = new Rectangle(clickX, clickY, this.widthForClick, this.heightForClick);
        this.direction = direction;
        this.oppositeDirection = oppositeDirection;
        this.setImage(direction);
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void changeDirection() {
        Directions oldDirection = this.direction;
        this.direction = oppositeDirection;
        this.oppositeDirection = oldDirection;
        this.setImage(direction);
    }

    public boolean intersects(int mouseX, int mouseY) {
        return this.boundingBox.x + this.boundingBox.width >= mouseX &&
                this.boundingBox.y + this.boundingBox.height >= mouseY &&
                this.boundingBox.x <= mouseX &&
                this.boundingBox.y <= mouseY;
    }

    public boolean intersectsClick(int mouseX, int mouseY) {
        return this.boundingBoxForClick.x + this.boundingBoxForClick.width >= mouseX &&
                this.boundingBoxForClick.y + this.boundingBoxForClick.height >= mouseY &&
                this.boundingBoxForClick.x <= mouseX &&
                this.boundingBoxForClick.y <= mouseY;
    }

    public void changeTrainDirection(Train train) {
        train.setDirection(this.direction);
    }

    private void setImage(Directions direction) {
        switch (direction) {
            case UP:
                this.image = Assets.upArrow;
                break;
            case DOWN:
                this.image = Assets.downArrow;
                break;
            case LEFT:
                this.image = Assets.leftArrow;
                break;
            case RIGHT:
                this.image = Assets.rightArrow;
                break;
//            case "nw":
//                this.image = Assets.nw;
//                break;
//            case "se":
//                this.image = Assets.se;
//                break;
//            case "sw":
//                this.image = Assets.sw;
//                break;
//            case "wn":
//                this.image = Assets.wn;
//                break;
//            case "horizontal":
//                this.image = Assets.horizontal;
//                break;
//            case "vertical":
//                this.image = Assets.vertical;
//                break;
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(this.image, boundingBoxForClick.x, boundingBoxForClick.y, null);
    }


}
