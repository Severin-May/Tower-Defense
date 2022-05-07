package model;

import java.awt.*;

public class HealthBar {

    private Troop troop;
    private Castle castle;

    public HealthBar (Troop troop){
        this.troop = troop;
    }

    public HealthBar (Castle castle){
        this.castle = castle;
    }

    public void paint(Graphics g) {

        //health bar for castle
        if (this.castle != null){
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            double castleHealthPercentage = castle.getCurrentHealth()/castle.getMaxHealth();
            g.setColor(Color.white);
            g.fillRoundRect (castle.getX()-18, castle.getY() - 25, 36, 12, 5, 5);
            getColor(g, castleHealthPercentage);
            g.fillRoundRect(castle.getX()-18, castle.getY() - 25, (int)(36*castleHealthPercentage), 12, 5, 5);
            g.setColor(Color.black);
            g.drawRoundRect (castle.getX()-18, castle.getY() - 25, 36, 12, 5, 5);
            g.drawString("" +castle.getHealthPoints(), castle.getX()-15, castle.getY() - 14);
        }

        //health bar for troops
        if (this.troop != null){
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
            double troopHealthPercentage = troop.getHealthPoints()/troop.getMaxHealth();
            g.setColor(Color.white);
            g.fillRoundRect (troop.getX()-13, troop.getY() - 20, 20, 10, 3, 3);
            getColor(g, troopHealthPercentage);
            g.fillRoundRect (troop.getX()-13, troop.getY() - 20, (int)(20*troopHealthPercentage), 10, 3, 3);
            g.setColor(Color.black);
            g.drawRoundRect (troop.getX()-13, troop.getY() - 20, 20, 10, 3, 3);
            g.drawString("" +troop.getHealthPoints(), troop.getX()-12, troop.getY() - 12);
        }
    }

    public void getColor(Graphics g, double percentage){
        if (percentage > 0.5) {
            g.setColor(new Color(((int)(255*-percentage)+255)*2,255,0));
        } else {
            g.setColor(new Color(255,(int)(255*percentage)*2,0));
        }
    }
}
