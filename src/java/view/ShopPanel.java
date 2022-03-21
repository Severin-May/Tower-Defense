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

        ImageIcon sRangeIcon = new ImageIcon("src/resources/images/Blue/Buildings/ShortRange/L1/Left.png");
        JButton buyShortRangeTower = new JButton("Short range", sRangeIcon);
        int offset = buyShortRangeTower.getInsets().left;
        buyShortRangeTower.setIcon(resizeIcon(sRangeIcon, 40 - offset, 40 - offset));

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

        ImageIcon lRangeIcon = new ImageIcon("src/resources/images/Blue/Buildings/LongRange/L1/Left.png");
        JButton buyLongRangeTower = new JButton("Long range", lRangeIcon);
        int offset2 = buyLongRangeTower.getInsets().left;
        buyLongRangeTower.setIcon(resizeIcon(lRangeIcon, 40 - offset2, 40 - offset2));

        buyLongRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.getInstance().setBuildingHover(new LongRange(-100,-100, new ImageIcon("src/resources/images/Blue/Buildings/LongRange/L1/Left.png").getImage(), Game.getInstance().getCurrentTurn()));
            }
        });
        gbc.gridx = 1;
        add(buyLongRangeTower, gbc);

        ImageIcon splashIcon = new ImageIcon("src/resources/images/Blue/Buildings/Splash/L1/Left.png");
        JButton buySplashTower = new JButton("Splash", splashIcon);
        int offset3 = buySplashTower.getInsets().left;
        buySplashTower.setIcon(resizeIcon(splashIcon, 40 - offset3, 40 - offset3));

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

    /**
     * resizes the icon so that it fits the button
     * @param icon takes an icon to be resizes
     * @param resizedWidth desired width of the button
     * @param resizedHeight desired height of the button
     * @return
     * TODO: change the color of towers (i.e. change the pngs) as current player changes
     */
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
