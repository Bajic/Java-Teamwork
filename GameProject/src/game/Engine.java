package game;

import audio.AudioConstants;
import audio.AudioPlayer;
import displays.Assets;
import displays.Display;
import models.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements Runnable {
    private String title;
    private int width;
    private int height;
    private boolean isRunning;
    private Random random;
    private Timer timer;
    private Thread thread;
    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage backgroundImage;
    private InputMouseListener mouseListener;
    private Turn[] turns;
    private Station[] stations;
    private ArrayList<Train> trains;
    public static RailroadSwitch[] railroadSwitches;  // Public field!

    public Engine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;

    }

    public void initialize() {
        display = new Display(this.title, this.width, this.height);
        this.random = new Random();
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                trains.add(new Train(ColorType.values()[random.nextInt(8)]));
            }
        }, 4 * 1000, 4 * 1000);
        backgroundImage = Assets.load("/images/background.png");
        trains = new ArrayList<>();
        trains.add(new Train(ColorType.values()[random.nextInt(8)]));
        this.mouseListener = new InputMouseListener(this.display);
        initRailroadSwitches();
        initTurns();
        initStations();

        AudioPlayer.playMusic(AudioConstants.BACKGROUND_GAME_MUSIC);
    }

    private void update() {
        for (Train train : trains) {
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

            for (Station station : stations) {
                if (train.intersects(station.getBoundingBox())) {
                    train.setVisible(false);
                    if (train.getColor().equals(station.getColor())) {
                        //TODO: score++
                    }
                }
            }
        }
        for (int i = 0; i < trains.size(); i++) {
            if (!trains.get(i).getVisible()) trains.remove(i);
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
        for (Train train : trains) {
            train.draw(this.graphics);
        }

        this.bufferStrategy.show();
        this.graphics.dispose();
    }

    @Override
    public void run() {

        initialize();

        int fps = 30;
        double ticksPerFrame = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / ticksPerFrame;
            lastTimeTicked = now;

            if (delta >= 1) {
                update();
                draw();
                delta--;
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

    private void initRailroadSwitches() {
        railroadSwitches = new RailroadSwitch[]{
                new RailroadSwitch(390, 390, 60, 3, 400, 415, "up", "right"),
                new RailroadSwitch(713, 370, 3, 40, 675, 395, "right", "down"),
                new RailroadSwitch(565, 660, 3, 120, 555, 690, "left", "up"),
                new RailroadSwitch(830, 540, 50, 3, 835, 530, "down", "right"),
                new RailroadSwitch(860, 240, 3, 100, 825, 255, "up", "right"),
                new RailroadSwitch(570, 240, 3, 120, 525, 255, "up", "right"),
                new RailroadSwitch(275, 60, 3, 60, 265, 110, "down", "left")};
    }

    private void initTurns() {
        this.turns = new Turn[]{
                new Turn(420, 530, 3, 100, "up"),
                new Turn(420, 245, 100, 3, "right"),
                new Turn(510, 100, 150, 3, "left"),
                new Turn(690, 680, 100, 3, "left"),
                new Turn(860, 370, 3, 130, "down")};
    }


    private void initStations() {
        this.stations = new Station[]{
                new Station(70, 70, ColorType.white),
                new Station(250, 185, ColorType.purple),
                new Station(940, 230, ColorType.blue),
                new Station(830, 80, ColorType.green),
                new Station(940, 515, ColorType.black),
                new Station(834, 620, ColorType.blackGreen),
                new Station(540, 510, ColorType.yellow),
                new Station(350, 670, ColorType.red),
        };
    }
}
