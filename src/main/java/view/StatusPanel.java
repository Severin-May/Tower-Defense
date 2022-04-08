package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.rightPanelWidth;

public class StatusPanel extends JPanel {
    public StatusPanel(JLabel gold, JLabel magsTrained, JLabel swordTrained, JLabel goldMines, JLabel towers, JLabel castleHp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        gold.setMaximumSize(new Dimension(200,30));
        magsTrained.setMaximumSize(new Dimension(200,30));
        goldMines.setMaximumSize(new Dimension(200,30));
        towers.setMaximumSize(new Dimension(200,30));
        castleHp.setMaximumSize(new Dimension(200,30));
        add(gold);
        add(swordTrained);
        add(magsTrained);
        add(goldMines);
        add(towers);
        add(castleHp);
    }
}