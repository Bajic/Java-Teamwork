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
    private Turn turn;

    private Train train;
    public static RailroadSwitch railroadSwitch;  // Public field!

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
        this.railroadSwitch = new RailroadSwitch();
        this.turn = new Turn(450,550,"up");
    }

    private void update() {

        train.update();
        if (train.intersects(railroadSwitch.getBoundingBox())){
            railroadSwitch.changeTrainDirection(train);
        }
        if (train.intersects(turn.getBoundingBox())){
            train.setDirection(turn.getDirection());
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
        railroadSwitch.draw(graphics);
        train.draw(this.graphics);
        turn.draw(graphics);

        this.bufferStrategy.show();
        graphics.setColor(Color.cyan);
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
