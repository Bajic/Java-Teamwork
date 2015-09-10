package game;

import displays.Display;
import displays.ImageCreator;
import models.RailroadSwitch;
import models.Train;
import models.Turn;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Engine implements Runnable {
    private String title;
    private int width;
    private int height;
    private boolean isRunning;
    private Thread thread;
    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage backgroundImage;
    private InputMouseListener mouseListener;
    private Turn[] turns;

    private Train train;
    public static RailroadSwitch[] railroadSwitches;  // Public field!

    public Engine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;

    }

    public void initialize() {
        ImageCreator.init();
        display = new Display(this.title, this.width, this.height);
        backgroundImage = ImageCreator.load("/images/background.png");
        train = new Train();
        this.mouseListener = new InputMouseListener(this.display);
        railroadSwitches = new RailroadSwitch[7];
        railroadSwitches[0] = new RailroadSwitch(410, 340, 400, 415, "up", "right");
        railroadSwitches[1] = new RailroadSwitch(750, 405, 675, 395, "right", "down");
        railroadSwitches[2] = new RailroadSwitch(505, 700, 555, 690, "left", "up");
        railroadSwitches[3] = new RailroadSwitch(840, 590, 835, 530, "down", "right");
        railroadSwitches[4] = new RailroadSwitch(888, 260, 825, 255, "up", "right");
        railroadSwitches[5] = new RailroadSwitch(595, 260, 525, 255, "up", "right");
        railroadSwitches[6] = new RailroadSwitch(218, 110, 265, 110, "down", "left");
        this.turns = new Turn[5];
        turns[0] = new Turn(450, 550, "up");
        turns[1] = new Turn(420, 200, "right");
        turns[2] = new Turn(550, 55, "left");
        turns[3] = new Turn(690, 730, "left");
        turns[4] = new Turn(890, 410, "down");
    }

    private void update() {

        train.update();
        for (RailroadSwitch railroadSwitch : railroadSwitches) {
            if (train.intersects(railroadSwitch.getBoundingBox())) {
                railroadSwitch.changeTrainDirection(train);
            }
        }

        for (Turn turn1 : turns) {
            if (train.intersects(turn1.getBoundingBox())) {
                train.setDirection(turn1.getDirection());
            }
        }

    }

    private void draw() {
        this.bufferStrategy = display.getCanvas().getBufferStrategy();

        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.drawImage(backgroundImage, 0, 0, null);
        for (RailroadSwitch railroadSwitch : railroadSwitches) {
            railroadSwitch.draw(graphics);
        }

        train.draw(this.graphics);

        this.bufferStrategy.show();
        this.graphics.dispose();
    }

    @Override
    public void run() {

        initialize();
        while (isRunning) {
            //TODO: calculate fps and remove thread sleep
            try {
                Thread.sleep(30);
                update();
                draw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        stop();
    }

    public synchronized void start() {
        if (isRunning) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!isRunning) {
            return;
        }
        this.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
