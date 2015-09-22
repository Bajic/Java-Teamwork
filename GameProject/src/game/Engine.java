package game;

import audio.AudioConstants;
import audio.AudioManager;
import audio.AudioPlayer;
import displays.Assets;
import displays.Display;
import displays.GameOverDialog;
import enums.ColorType;
import enums.Difficulty;
import enums.Directions;
import models.*;
import utilities.DifficultyMultiplier;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements Runnable {
    private Difficulty difficulty;
    private double timeAdjuster;
    private String title;
    private int width;
    private int height;
    private boolean isRunning;
    private Timer timer;
    private Thread thread;
    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage backgroundImage;
    private InputMouseListener mouseListener;
    private Turn[] turns;
    private Station[] stations;
    private Player player;
    private ArrayList<Train> trains;
    private ArrayList<Train> trainsToRemove;
    private ArrayList<RailroadSwitch> railroadSwitches;

    public Engine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
        this.difficulty = Difficulty.EASY;
        this.timeAdjuster = DifficultyMultiplier.EASY;
        this.player = new Player("didok4o");
    }

    public Iterable<RailroadSwitch> getRailroadSwitches() {
        return railroadSwitches;
    }

    public void initialize() {
        Assets.init();
        backgroundImage = Assets.load("/images/backgroundScoreLifes.png");
        AudioManager.loadSounds();
        display = new Display(this.title, this.width, this.height);
        this.timer = new Timer();

        trains = new ArrayList<>();
        trainsToRemove = new ArrayList<>();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                trains.add(TrainFactory.ProduceTrain(difficulty));
            }
        }, 1500, (int) (1500 * 2 / timeAdjuster));

        this.mouseListener = new InputMouseListener(this.display, this);

        initRailroadSwitches();
        initTurns();
        initStations();

        AudioPlayer.playMusic(AudioConstants.BACKGROUND_GAME_MUSIC);
    }

    private void update() {
        for (Train train : trains) {
            train.update();
            updateRailroadSwitches(train);
            updateTurns(train);
            for (Station station : stations) {
                if (train.intersects(station.getBoundingBox())) {

                    if (train.getColor().equals(station.getColor())) {
                        AudioPlayer.playSound(AudioConstants.RIGHT_STATION);
                        this.player.setScore(1);
                        updateGameSpeed();

                    } else {
                        this.player.removeLife();

                        if (this.player.getLives() > 0) {
                            AudioPlayer.playSound(AudioConstants.WRONG_STATION);
                        } else {
                            gameOver();
                        }
                    }
                    this.trainsToRemove.add(train);
                    train.setVisible(false);
                }
            }
        }
        this.trains.removeAll(trainsToRemove);
        trainsToRemove.clear();
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

    private void gameOver() {
        AudioPlayer.stopMusic(AudioConstants.BACKGROUND_GAME_MUSIC);
        AudioPlayer.playSound(AudioConstants.GAME_OVER);
        this.display.closeFrame();
        new GameOverDialog();
        stop();
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

        drawGameStats();

        for (RailroadSwitch railroadSwitch : railroadSwitches) {
            railroadSwitch.draw(graphics);
        }

        for (Train train : trains) {
            train.draw(this.graphics);
        }

        this.bufferStrategy.show();
        this.graphics.dispose();
    }

    private void drawGameStats() {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("default", Font.BOLD, 45));
        //graphics.drawString(this.player.getName(), 1070, 3);
        graphics.drawString("" + this.player.getScore(), player.getScore() > 10 ? 1047 : 1064, 48);
        graphics.setFont(new Font("default", Font.BOLD, 55));
        graphics.drawString("" + this.player.getLives(), 955, 75);
    }

    // TODO: move initialization in a separate class
    private void initRailroadSwitches() {
        railroadSwitches = new ArrayList<RailroadSwitch>() {{
            add(new RailroadSwitch(390, 390, 60, 3, 400, 415, Directions.UP, Directions.RIGHT));
            add(new RailroadSwitch(713, 370, 3, 40, 675, 395, Directions.RIGHT, Directions.DOWN));
            add(new RailroadSwitch(565, 660, 3, 120, 555, 690, Directions.LEFT, Directions.UP));
            add(new RailroadSwitch(830, 540, 50, 3, 835, 530, Directions.DOWN, Directions.RIGHT));
            add(new RailroadSwitch(860, 240, 3, 100, 825, 255, Directions.UP, Directions.RIGHT));
            add(new RailroadSwitch(570, 240, 3, 120, 525, 255, Directions.UP, Directions.RIGHT));
            add(new RailroadSwitch(275, 60, 3, 60, 265, 110, Directions.DOWN, Directions.LEFT));
        }};

//                new RailroadSwitch(390, 390, 60, 3, 385, 390, "vertical", "se"),
//                new RailroadSwitch(713, 370, 3, 40, 670, 385, "horizontal", "sw"),
//                new RailroadSwitch(565, 660, 3, 120, 555, 690, "left", "up"),
//                new RailroadSwitch(830, 540, 50, 3, 835, 530, "down", "right"),
//                new RailroadSwitch(860, 240, 3, 100, 825, 255, "up", "right"),
//                new RailroadSwitch(570, 240, 3, 120, 525, 255, "up", "right"),
//                new RailroadSwitch(275, 60, 3, 60, 265, 110, "down", "left")};
    }

    private void initTurns() {
        this.turns = new Turn[]{
                new Turn(420, 530, 3, 100, Directions.UP),
                new Turn(420, 245, 100, 3, Directions.RIGHT),
                new Turn(510, 100, 150, 3, Directions.LEFT),
                new Turn(690, 680, 100, 3, Directions.LEFT),
                new Turn(860, 370, 3, 130, Directions.DOWN)};
    }

    private void updateGameSpeed() {
        if (this.player.getScore() % 30 == 0) {
            this.player.receiveLife();
            System.out.println("Here! Get a life!");
        }

        if (this.player.getScore() <= 75) {
            if (this.player.getScore() == 25) {
                this.difficulty = Difficulty.MEDIUM;
                this.timeAdjuster = DifficultyMultiplier.MEDIUM;
                System.out.println("timer: " + this.timeAdjuster);
            }

            if (this.player.getScore() == 50) {
                this.difficulty = Difficulty.HARD;
                this.timeAdjuster = DifficultyMultiplier.HARD;
                System.out.println("timer: " + this.timeAdjuster);
            }
        }
    }

    private void updateRailroadSwitches(Train train) {
        for (RailroadSwitch railroadSwitch : railroadSwitches) {
            if (train.intersects(railroadSwitch.getBoundingBox())) {
                railroadSwitch.changeTrainDirection(train);
            }
        }
    }

    private void updateTurns(Train train) {
        for (Turn turn1 : turns) {
            if (train.intersects(turn1.getBoundingBox())) {
                train.setDirection(turn1.getDirection());
            }
        }
    }

    private void initStations() {
        this.stations = new Station[]{
                new Station(70, 70, ColorType.WHITE),
                new Station(250, 185, ColorType.PURPLE),
                new Station(940, 230, ColorType.BLUE),
                new Station(830, 80, ColorType.GREEN),
                new Station(940, 515, ColorType.BLACK),
                new Station(834, 620, ColorType.BLACK_GREEN),
                new Station(540, 510, ColorType.YELLOW),
                new Station(350, 670, ColorType.RED),
        };
    }
}
