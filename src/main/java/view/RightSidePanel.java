package view;

import model.Game;
import model.LongRange;
import model.ShortRange;
import model.Splash;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.GameSettings.*;
import static utils.GameSettings.getSwordLeftStop;

public class RightSidePanel extends JPanel {
    Game game;
    //whose turn is it:
    JLabel playerName;
    //Status panel depends on:
    JLabel gold;
    JLabel magsTrained;
    JLabel swordTrained;
    JLabel goldMines;
    JLabel towers;
    JLabel castleHp;
    //Shop panel depends on:
    JButton buyShortRangeTower;
    JButton buyLongRangeTower;
    JButton buySplashTower;
    JButton trainSword;
    JButton trainMag;
    JButton endTurn;
    JButton startFightingStage;

    public RightSidePanel() {
        game = Game.getInstance();
        setLayout(new GridLayout(3, 1, 0, 0));
        setBorder(new EmptyBorder(0, padding, 0, 0));
        setPreferredSize(new Dimension(rightPanelWidth, mapHeightInCells + 30));
        playerName = new JLabel();
        add(playerName);
        createLabels();
        add(new StatusPanel(gold, magsTrained, swordTrained, goldMines, towers, castleHp));
        createButtons();
        add(new ShopPanel(buyShortRangeTower, buyLongRangeTower, buySplashTower, trainSword, trainMag, endTurn, startFightingStage));
    }


    private void createLabels() {
        gold = new JLabel();
        swordTrained = new JLabel();
        magsTrained = new JLabel();
        goldMines = new JLabel();
        towers = new JLabel();
        castleHp = new JLabel();
        setLabelText();
    }

    private void setLabelText() {
        playerName.setText("Whoever turn's name: " + game.getCurrentTurn().getName());
        gold.setText("GOLD: " + game.getCurrentTurn().getGold());
        swordTrained.setText("Sword men trained: " + "0");
        magsTrained.setText("Mags trained: " + "0");
        goldMines.setText("Gold mines count: " + "0");
        towers.setText("Towers: " + "0");
        castleHp.setText("YourCastleHP: " + game.getCurrentTurn().getCastle().getHealthPoints());
    }

    private void createButtons() {
        buyLongRangeTower = new JButton("Long range");
        buyLongRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new LongRange(game.getCurrentTurn()));
            }
        });
        buyShortRangeTower = new JButton("Short range");
        buyShortRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new ShortRange(game.getCurrentTurn()));
            }
        });
        buySplashTower = new JButton("Splash");
        buySplashTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new Splash(game.getCurrentTurn()));
            }
        });
        trainSword = new JButton("Sword troop");
        trainSword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Sword man was clicked");
            }
        });
        trainMag = new JButton("Mag troop");
        trainMag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mag was clicked");
            }
        });
        endTurn = new JButton("End turn");
        endTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.changeTurn();
                System.out.println("Changed to " + game.getCurrentTurn().getColor());
                setLabelText();
                setButtonIcons();
                startFightingStage.setVisible(true);
                endTurn.setVisible(false);
            }
        });
        startFightingStage = new JButton("Start fighting stage");
        startFightingStage.setVisible(false);
        startFightingStage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setPreparationTime(true);
                System.out.println("Starting the fighting stage!");
            }
        });
        setButtonIcons();
    }

    private void setButtonIcons() {
        buyShortRangeTower.setIcon(resizeIcon(new ImageIcon(getShortRangeL1Left())));
        buyLongRangeTower.setIcon(resizeIcon(new ImageIcon(getLongRangeL1Left())));
        buySplashTower.setIcon(resizeIcon(new ImageIcon(getSplashL1Left())));
        trainSword.setIcon(resizeIcon(new ImageIcon(getSwordLeftStop())));
        trainMag.setIcon(resizeIcon(new ImageIcon(getMagLeftStop())));
    }

    /**
     * resizes the icon width to 30 and height to 35 so that it fits the button
     *
     * @param icon takes an icon to be resizes
     * @return resized Icon
     */
    private Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(30, 35, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
