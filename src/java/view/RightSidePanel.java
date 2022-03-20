package view;

import utils.GameSettings;

import javax.swing.*;
import java.awt.*;

public class RightSidePanel extends JPanel {
    public RightSidePanel() {
        setLayout(new GridLayout(3,1));
        JLabel playerName = new JLabel("CURRENT PLAYER'S NAME");
        playerName.setPreferredSize(new Dimension(300,200));
        add(playerName);
        add(new StatusPanel());
        add(new ShopPanel());
    }
}
