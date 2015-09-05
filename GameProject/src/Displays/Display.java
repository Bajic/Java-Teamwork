package Displays;

import javax.swing.*;
import java.awt.*;

public class Display {
    private String title;
    private int width;
    private int height;
    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.frame = new JFrame(this.title);
        this.adjustFrame();
    }

    private void adjustFrame() {
        frame.setSize(this.width, this.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        frame.add(canvas);
        frame.pack();
    }
    public Canvas getCanvas() {
        return this.canvas;
    }

}
