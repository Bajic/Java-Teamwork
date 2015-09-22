package gui;

import displays.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImagePanel extends JPanel {
    //Image test = new ImageIcon()
    BufferedImage bg = Assets.load("/images/backgroundTest.png");

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
