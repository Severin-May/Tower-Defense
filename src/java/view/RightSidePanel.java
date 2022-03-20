package view;

import model.Game;
import utils.GameSettings;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.mapHeightInCells;
import static utils.GameSettings.rightPanelWidth;

public class RightSidePanel extends JPanel {
    Game game;
    public RightSidePanel() {
        game = Game.getInstance();
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(rightPanelWidth, mapHeightInCells+30 ));
        JLabel playerName = new JLabel("Whoever turn's name: " + game.getCurrentTurn().getName());
        add(playerName);
        add(new StatusPanel());
        add(new ShopPanel());
    }
}
