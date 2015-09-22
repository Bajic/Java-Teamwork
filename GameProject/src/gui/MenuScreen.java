package gui;

import audio.AudioConstants;
import audio.AudioManager;
import audio.AudioPlayer;
import displays.Assets;
import game.Engine;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JFrame {
    private static final java.lang.Object HOW_TO_PLAY_MESSAGE = "Use the left mouse button to switch the direction of the tracks.\n" +
            "Guide each train toward the station with the same color.";
    private Engine engine;

    public MenuScreen(Engine engine) {
        this.engine = engine;
        init();
    }

    public final void init() {
        AudioManager.loadSounds();
        AudioPlayer.playMusic(AudioConstants.BACKGROUND_MENU_MUSIC);
        Assets.init();

        JPanel panel = new ImagePanel();
        panel.setLayout(null);

        //Setting the parameters of the frame
        this.setTitle("Train of Thought");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(panel);

        setResizable(false);
        //START GAME
        JButton startGameButton = new JButton("New Game");
        startGameButton.setBounds(50, 60, 400, 70);
        startGameButton.setFont(new Font("Arial", Font.BOLD, 22));
        startGameButton.addActionListener(event -> {
            AudioPlayer.playSound(AudioConstants.MENU_BUTTONS_SOUND);
            engine.start();
        });

        //HIGHSCORE
        JButton highScoreButton = new JButton("Highscore");
        highScoreButton.setBounds(50, 170, 400, 70);
        highScoreButton.setFont(new Font("Arial", Font.BOLD, 22));
        highScoreButton.addActionListener(event -> AudioPlayer.playSound(AudioConstants.MENU_BUTTONS_SOUND));

        //HOW TO PLAY
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setBounds(50, 280, 400, 70);
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 22));
        howToPlayButton.addActionListener(event -> {
            AudioPlayer.playSound(AudioConstants.MENU_BUTTONS_SOUND);
            JOptionPane.showMessageDialog(panel, HOW_TO_PLAY_MESSAGE, "How to Play",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        //QUIT
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 390, 400, 70);
        quitButton.setFont(new Font("Arial", Font.BOLD, 22));
        quitButton.addActionListener(event -> {
            AudioPlayer.playSound(AudioConstants.MENU_BUTTONS_SOUND);
            System.exit(0);
        });

        //ADD BUTTONS TO JPANEL
        panel.setOpaque(false);
        panel.add(startGameButton);
        panel.add(highScoreButton);
        panel.add(howToPlayButton);
        panel.add(quitButton);
        panel.setVisible(true);

    }
}