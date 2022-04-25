package model;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JComponent {

    private final Troop troop;

    public HealthBar (Troop troop){
        this.troop = troop;
        update();
        setVisible(true);
    }

    public void update(){
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        double troopHealthPercentage = troop.getHealthPoints()/troop.getMaxHealth();
        g.setColor(Color.white);
        g.fillRect (troop.getX()-12, troop.getY() - 18, 20, 7);
        if (troopHealthPercentage > 0.5) {
            g.setColor(new Color(((int)(255*-troopHealthPercentage)+255)*2,255,0));
        } else {
            g.setColor(new Color(255,(int)(255*troopHealthPercentage)*2,0));
        }
        g.fillRect (troop.getX()-12, troop.getY() - 18, (int)(20*troopHealthPercentage), 7);
        g.setColor(Color.black);
        g.drawRect (troop.getX()-12, troop.getY() - 18, 20, 7);
    }
}
