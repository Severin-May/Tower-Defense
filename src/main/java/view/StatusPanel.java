package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    public StatusPanel(JLabel gold, JLabel magsTrained, JLabel swordTrained, JLabel goldMines, JLabel towers, JLabel castleHp) {
        setLayout(new GridLayout(6, 1, 0, 0));
        add(gold);
        add(swordTrained);
        add(magsTrained);
        add(goldMines);
        add(towers);
        add(castleHp);
    }
}