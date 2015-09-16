package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game.Engine;
import displays.Assets;

public class MenuScreen extends JFrame {
    private static final java.lang.Object HOW_TO_PLAY_MESSAGE = "Use the left mouse button to switch the direction of the tracks.\n" +
            "Guide each train toward the station with the same color.";;

    public MenuScreen() {

        initUI();
    }

    public final void initUI() {
        Assets.init();

        JPanel panel = new ImagePanel();
        panel.setLayout(null);

        //Setting the parameters of the frame
        this.setTitle("Train of Thought");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setContentPane(panel);

        setResizable(false);
        //START GAME
        JButton startGameButton = new JButton("New Game");
        startGameButton.setBounds(50, 60, 400, 70);
        startGameButton.setFont(new Font("Arial", Font.BOLD, 22));
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Engine engine = new Engine("Train of Thought", 1137, 790);
                engine.start();
            }
        });

        //HIGHSCORE
        JButton highScoreButton = new JButton("Highscore");
        highScoreButton.setBounds(50, 170, 400, 70);
        highScoreButton.setFont(new Font("Arial", Font.BOLD, 22));
        highScoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

            }
        });

        //HOW TO PLAY
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setBounds(50, 280, 400, 70);
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 22));
        howToPlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(panel, HOW_TO_PLAY_MESSAGE, "How to Play",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //QUIT
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 390, 400, 70);
        quitButton.setFont(new Font("Arial", Font.BOLD, 22));
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
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