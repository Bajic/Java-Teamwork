package gui;

import displays.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GameOverImagePanel extends JPanel {

    BufferedImage bg = Assets.load("/images/gameOver.jpg");

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}