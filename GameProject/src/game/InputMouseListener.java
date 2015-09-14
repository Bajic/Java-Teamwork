package game;

import displays.Display;
import models.RailroadSwitch;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputMouseListener implements MouseListener {

    public InputMouseListener(Display display) {
        display.getCanvas().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (RailroadSwitch railroadSwitch : Engine.railroadSwitches) {
            if (railroadSwitch.intersectsClick(e.getX(),e.getY())){
                railroadSwitch.changeDirection();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
