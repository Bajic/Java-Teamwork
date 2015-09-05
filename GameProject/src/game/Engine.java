package game;

import Displays.Display;

public class Engine implements Runnable {
    private String title;
    private int width;
    private int height;
    private boolean isRunning;

    public Engine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
        new Display(this.title,this.width,this.height);
    }


    @Override
    public void run() {
        while (isRunning){

        }
    }
}
