package models;

import displays.ImageLoader;

import java.awt.*;

public class RailroadSwitch {

    private int x;
    private int y;
    private int width;
    private int height;
    private String direction;

    private Rectangle boundingBox;

    public RailroadSwitch() {
        this.x = 135;
        this.y = 150;
        this.width = 100;
        this.height = 100;
        this.boundingBox = new Rectangle(x-100,y,this.width,this.height);
        this.direction = "up";
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void changeDirection(Train train){
        train.setDirection(this.direction);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(ImageLoader.load("/images/switch.png"), this.x, this.y, null);
    }


}
