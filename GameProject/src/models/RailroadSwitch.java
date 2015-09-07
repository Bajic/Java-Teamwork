package models;

import displays.ImageLoader;

import java.awt.*;

public class RailroadSwitch {

    private int x;
    private int y;
    private int width;
    private int height;
    private String direction;
    private String oppositeDirection;

    private Rectangle boundingBox;

    public RailroadSwitch() {
        this.x = 135;
        this.y = 160;
        this.width = 100;
        this.height = 100;
        this.boundingBox = new Rectangle(x-100,y,this.width,this.height);
        this.direction = "up";
        this.oppositeDirection = "down";
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void changeDirection(){
        String oldDirection = this.direction;
        this.direction=oppositeDirection;
        System.out.println(direction);
        this.oppositeDirection = oldDirection;
    }

    public boolean intersects(int mouseX,int mouseY) {
        return this.boundingBox.x +100 + this.boundingBox.width >= mouseX &&
                this.boundingBox.y + this.boundingBox.height >= mouseY &&
                this.boundingBox.x +100 <= mouseX &&
                this.boundingBox.y <= mouseY;
    }

    public void changeTrainDirection(Train train){
        train.setDirection(this.direction);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(ImageLoader.load("/images/switch.png"), this.x, this.y, null);
    }


}
