package view;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    public StatusPanel() {
        setLayout(new GridLayout(5,1,0,0));
        JLabel gold = new JLabel("GOLD: 999999");
        gold.setSize(new Dimension(1,1));
        add(gold);
        JLabel troops = new JLabel("TROOPS: 0");
        troops.setSize(new Dimension(1,1));
        add(troops);
        JLabel goldMines = new JLabel("Gold Mines: 0");
        troops.setSize(new Dimension(1,1));
        add(goldMines);
        JLabel towers =new JLabel("Towers: 0");
        troops.setSize(new Dimension(1,1));
        add(towers);
        JLabel castleHp =new JLabel("YourCastleHP: 100");
        troops.setSize(new Dimension(1,1));
        add(castleHp);
    }
}
