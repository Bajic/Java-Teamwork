package displays;

import game.GameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverDialog {
    public GameOverDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("GAME OVER");
        dialog.setVisible(true);
        dialog.setSize(300, 100);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        Button quitButton = new Button("Quit");
        quitButton.setSize(100,100);
        quitButton.setVisible(true);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setSize(100, 100);
        playAgainButton.setVisible(true);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: close old window
                GameMain.main(new String[]{});
            }
        });
        dialog.add(playAgainButton);
        dialog.add(quitButton);
    }
}
