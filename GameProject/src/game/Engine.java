package game;

import displays.Display;
import displays.ImageCreator;
import models.RailroadSwitch;
import models.Train;
import models.Turn;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    private ArrayList<Turn> turns;

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
        this.railroadSwitch = new RailroadSwitch(410,340,400,415,"up","right");
        this.turns = new ArrayList<>();
        turns.add(new Turn(450, 550, "up"));
        turns.add(new Turn(420, 200, "right")); //TODO: add three more
    }

    private void update() {

        train.update();
        if (train.intersects(railroadSwitch.getBoundingBox())) {
            railroadSwitch.changeTrainDirection(train);
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
        railroadSwitch.draw(graphics);
        train.draw(this.graphics);
        // TODO: delete turns draw
        for (Turn turn1 : turns) {
            turn1.draw(graphics);
        }

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
