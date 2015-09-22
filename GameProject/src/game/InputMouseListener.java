package game;

import audio.AudioConstants;
import displays.Display;
import models.RailroadSwitch;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputMouseListener implements MouseListener {

    private Engine engine;
    public InputMouseListener(Display display, Engine engine)
    {
        display.getCanvas().addMouseListener(this);
        this.engine = engine;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (RailroadSwitch railroadSwitch : engine.getRailroadSwitches()) {
            if (railroadSwitch.intersectsClick(e.getX(),e.getY())){
                railroadSwitch.changeDirection();
                audio.AudioPlayer.playSound(AudioConstants.SWITCH);
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
