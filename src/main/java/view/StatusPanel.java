package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    Game game;
    public StatusPanel() {
        game = Game.getInstance();
        setLayout(new GridLayout(5,1,0,0));
        JLabel gold = new JLabel("GOLD: " + game.getCurrentTurn().getGold());
        gold.setSize(new Dimension(1,1));
        add(gold);
        JLabel troops = new JLabel("TROOPS: " + "0");
        troops.setSize(new Dimension(1,1));
        add(troops);
        JLabel goldMines = new JLabel("Gold Mines: " + "0");
        troops.setSize(new Dimension(1,1));
        add(goldMines);
        JLabel towers =new JLabel("Towers: " + "0");
        troops.setSize(new Dimension(1,1));
        add(towers);
        JLabel castleHp =new JLabel("YourCastleHP: " + game.getCurrentTurn().getCastle().getHealthPoints());
        troops.setSize(new Dimension(1,1));
        add(castleHp);
    }
}
