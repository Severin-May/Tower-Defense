package view;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public ShopPanel(JButton buyShortRangeTower, JButton buyLongRangeTower, JButton buySplashTower, JButton trainSword, JButton trainMag, JButton endTurn, JButton startFightingStage) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(buyShortRangeTower, gbc);
        gbc.gridx = 1;
        add(buyLongRangeTower, gbc);
        gbc.gridx = 2;
        add(buySplashTower, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(trainSword, gbc);
        gbc.gridx = 1;
        add(trainMag, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(endTurn, gbc);
        add(startFightingStage, gbc);
    }


}
