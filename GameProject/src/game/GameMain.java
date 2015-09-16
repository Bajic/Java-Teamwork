package game;

import gui.MenuScreen;

public class GameMain {
    public static void main(String[] args) {
        Engine engine = new Engine("Train of Thought",1137,790);
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setVisible(true);
    }
}

