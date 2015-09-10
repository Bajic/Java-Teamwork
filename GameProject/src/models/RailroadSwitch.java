package models;

import java.awt.*;

public class RailroadSwitch {

    private int x;
    private int y;
    private int width;
    private int height;
    private String direction;
    private String oppositeDirection;

    private Rectangle boundingBox;
    private Rectangle boundingBoxForClick;

    public RailroadSwitch(int x, int y, int clickX, int clickY, String direction, String oppositeDirection) {
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 60;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
        this.boundingBoxForClick = new Rectangle(clickX, clickY, this.width + 15, this.height + 15);
        this.direction = direction;
        this.oppositeDirection = oppositeDirection;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void changeDirection() {
        String oldDirection = this.direction;
        this.direction = oppositeDirection;
        System.out.println(direction);
        this.oppositeDirection = oldDirection;
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

    public void draw(Graphics graphics) {
       //TODO: add pictures
    }


}
