package view;

import model.Game;
import model.LongRange;
import model.ShortRange;
import model.Splash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ShopPanel extends JPanel {

    public ShopPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton buyShortRangeTower = new JButton("Short range tower");
        buyShortRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().setBuildingHover(new ShortRange(-100,-100, new ImageIcon("src/resources/images/Blue/Buildings/ShortRange/L1/Left.png").getImage(), Game.getInstance().getCurrentTurn()));
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(buyShortRangeTower,gbc);
        JButton buyLongRangeTower = new JButton("Long range tower");
        buyLongRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().setBuildingHover(new LongRange(-100,-100, new ImageIcon("src/resources/images/Blue/Buildings/LongRange/L1/Left.png").getImage(), Game.getInstance().getCurrentTurn()));
            }
        });
        gbc.gridx = 1;
        add(buyLongRangeTower, gbc);
        JButton buySplashTower = new JButton("Splash tower");
        buySplashTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().setBuildingHover(new Splash(-100,-100, new ImageIcon("src/resources/images/Blue/Buildings/Splash/L1/Left.png").getImage(), Game.getInstance().getCurrentTurn()));
            }
        });
        gbc.gridx = 2;
        add(buySplashTower, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(new JButton("TRAIN TROOP"),gbc);
    }
}
