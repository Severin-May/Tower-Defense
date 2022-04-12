package view;

import model.Game;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.rightPanelWidth;

public class StatusPanel extends JPanel {
    public StatusPanel(CustomLabel gold, CustomLabel troopsTrained, CustomLabel goldMines, CustomLabel towers, CustomLabel castleHp) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        add(gold);
        add(troopsTrained);
        add(goldMines);
        add(towers);
        add(castleHp);
    }
}