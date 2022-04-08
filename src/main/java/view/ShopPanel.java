package view;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public ShopPanel(JButton buyShortRangeTower, JButton buyLongRangeTower, JButton buySplashTower, JButton trainSword, JButton trainMag, JButton endTurn, JButton startFightingStage) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel towers = new JLabel("Towers", SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        towers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(towers, gbc);

        JLabel soldiers = new JLabel("Soldiers", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        soldiers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(soldiers, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        JPanel towersPanel = new JPanel(new GridLayout(3, 1));
        towersPanel.add(buyShortRangeTower);
        towersPanel.add(buyLongRangeTower);
        towersPanel.add(buySplashTower);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(towersPanel, gbc);

        JPanel unitsPanel = new JPanel(new GridLayout(2, 1));
        unitsPanel.add(trainSword);
        unitsPanel.add(trainMag);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(unitsPanel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(endTurn, gbc);
        add(startFightingStage, gbc);
    }


}
