package displays;

import game.GameMain;
import gui.GameOverImagePanel;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JFrame {

    public GameOverDialog() {
        init();
    }

    private void init() {
        JPanel gameOverPanel = new GameOverImagePanel();
        gameOverPanel.setLayout(null);

        this.setTitle("Game Over");
        this.setSize(877, 666);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(gameOverPanel);

        setResizable(false);
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(50, 470, 350, 50);
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 22));
        playAgainButton.addActionListener(e -> {
            GameMain.main(new String[]{});
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 540, 350, 50);
        quitButton.setFont(new Font("Arial", Font.BOLD, 22));
        quitButton.addActionListener(event -> System.exit(0));

        gameOverPanel.setOpaque(false);
        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(quitButton);
        gameOverPanel.setVisible(true);
    }
}
