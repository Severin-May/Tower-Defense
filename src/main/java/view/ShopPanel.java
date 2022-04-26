package view;

import javax.swing.*;
import java.awt.*;

/**
 * Shop panel is a wrapper for all the buttons that are mainly responsible for purchasing.
 * Additionally, it wraps and is responsible for control buttons that change the turn and start the fighting stage
 */
public class ShopPanel extends JPanel {
    /**
     * It uses {@link GridLayout} with {@link GridBagConstraints} to locate the buttons, so that it places towers and troops and control buttons in an understandable way
     * By creating an instance of a shop panel, you have to indicate all the dependent buttons that this class is responsible to wrap
     * @param buyShortRangeTower button to buy {@link model.ShortRange} tower
     * @param buyLongRangeTower button to buy {@link model.LongRange} tower
     * @param buySplashTower button to buy {@link model.Splash} tower
     * @param trainSword button to buy {@link model.Troop} of type {@link model.TroopType#SWORD_MAN}
     * @param trainMag button to buy {@link model.Troop} of type {@link model.TroopType#MAG}
     * @param trainSpecUnit button to buy {@link model.Troop} of type {@link model.TroopType#SPECIAL_UNIT}
     * @param goldMine button to buy {@link model.GoldMine}
     * @param endTurn button to change the turn of the game
     * @param startFightingStage button to start the fighting stage
     */
    public ShopPanel(JButton buyShortRangeTower, JButton buyLongRangeTower, JButton buySplashTower, JButton trainSword, JButton trainMag, JButton trainSpecUnit, JButton goldMine,  JButton endTurn, JButton startFightingStage) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel towers = new JLabel("Buildings", SwingConstants.CENTER);
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
        JPanel towersPanel = new JPanel(new GridLayout(4, 1));
        towersPanel.add(buyShortRangeTower);
        towersPanel.add(buyLongRangeTower);
        towersPanel.add(buySplashTower);
        towersPanel.add(goldMine);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(towersPanel, gbc);

        JPanel unitsPanel = new JPanel(new GridLayout(3, 1));
        unitsPanel.add(trainSword);
        unitsPanel.add(trainMag);
        unitsPanel.add(trainSpecUnit);
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
