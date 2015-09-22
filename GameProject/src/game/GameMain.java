package game;

import displays.MenuScreen;
import utilities.GlobalConstants;

public class GameMain {
    public static void main(String[] args) {
        Engine engine = new Engine("Train of Thought", GlobalConstants.WINDOW_WIDTH, GlobalConstants.WINDOW_HEIGHT);
        MenuScreen menuScreen = new MenuScreen(engine);
        menuScreen.setVisible(true);
    }
}