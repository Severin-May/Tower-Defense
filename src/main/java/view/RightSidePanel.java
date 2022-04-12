package view;

import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.GameSettings.*;
import static utils.GameSettings.getSwordLeftStop;

public class RightSidePanel extends JPanel implements ActionListener {
    Timer timer;
    Game game;
    //whose turn is it:
    CustomLabel playerName;
    //Status panel depends on:
    CustomLabel gold;
    CustomLabel troopsTrained;
    CustomLabel goldMines;
    CustomLabel towers;
    CustomLabel castleHp;
    //Shop panel depends on:
    CustomButton buyShortRangeTower;
    CustomButton buyLongRangeTower;
    CustomButton buySplashTower;
    CustomButton trainSword;
    CustomButton trainMag;
    CustomButton endTurn;
    CustomButton startFightingStage;

    private int colorId = -2;

    private final Color RED = new Color(255, 105, 105);
    private final Color BLUE = new Color(111, 196, 255);


    public RightSidePanel() {
        game = Game.getInstance();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, padding, 0, 0));
        setPreferredSize(new Dimension(rightPanelWidth, mapHeightInCells + 30));
        playerName = new CustomLabel();
        playerName.setPreferredSize(new Dimension(200, 30));
        add(playerName, BorderLayout.NORTH);
        createLabels();
        add(new StatusPanel(gold, troopsTrained, goldMines, towers, castleHp), BorderLayout.CENTER);
        createButtons();
        add(new ShopPanel(buyShortRangeTower, buyLongRangeTower, buySplashTower, trainSword, trainMag, endTurn, startFightingStage), BorderLayout.SOUTH);
        timer = new Timer(500,this);// status panel update timer
        timer.start();
    }


    private void createLabels() {
        gold = new CustomLabel();
        troopsTrained = new CustomLabel();
        goldMines = new CustomLabel();
        towers = new CustomLabel();
        castleHp = new CustomLabel();
        updateStatusLabels();
    }

    private void updateStatusLabels() {
        playerName.setText("Current Player: " + game.getCurrentTurn().getName());
        gold.setText("GOLD: " + game.getCurrentTurn().getGold());
        troopsTrained.setText("Units on the field: " + game.getCurrentTurn().getTroops().size());
        goldMines.setText("Gold mines built: " + game.getCurrentTurn().getGoldMines().size());
        towers.setText("Towers built: " + game.getCurrentTurn().getTowers().size());
        castleHp.setText("Your Castle HP: " + game.getCurrentTurn().getCastle().getHealthPoints());
    }

    private void createButtons() {
        int buttonWidth = 180;
        int buttonHeight = 80;
        buyShortRangeTower = new CustomButton(buttonWidth, buttonHeight, "Short Range", resizeIcon(new ImageIcon(getShortRangeL1Left())), 3);
        buyShortRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new ShortRange(game.getCurrentTurn()));
            }
        });
        buyLongRangeTower = new CustomButton(buttonWidth, buttonHeight, "Long Range", resizeIcon(new ImageIcon(getLongRangeL1Left())), 3);
        buyLongRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new LongRange(game.getCurrentTurn()));
            }
        });
        buySplashTower = new CustomButton(buttonWidth, buttonHeight, "Splash", resizeIcon(new ImageIcon(getSplashL1Left())), 3);
        buySplashTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setBuildingHover(new Splash(game.getCurrentTurn()));
            }
        });
        trainSword = new CustomButton(buttonWidth, buttonHeight*3/2, "Melee Unit", resizeIcon(new ImageIcon(getSwordLeftStop())), 3);
        trainSword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!game.getCurrentTurn().buyTroop(TroopType.SWORD_MAN)){
                    JOptionPane.showMessageDialog(getParent(), "Oops. Looks like you are too poor to purchase more sword men");
                }
            }
        });
        trainMag = new CustomButton(buttonWidth, buttonHeight*3/2, "Wizard", resizeIcon(new ImageIcon(getMagLeftStop())), 3);
        trainMag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!game.getCurrentTurn().buyTroop(TroopType.MAG)){
                    JOptionPane.showMessageDialog(getParent(), "Oops. Looks like you are too poor to purchase more magicians");
                }
            }
        });
        endTurn = new CustomButton(buttonWidth, buttonHeight, "End Turn", null, 4);
        endTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.changeTurn();
                endTurn.setVisible(false);
                startFightingStage.setVisible(true);
                changeButtons();
                System.out.println("Changed to " + game.getCurrentTurn().getColor());
            }
        });
        startFightingStage = new CustomButton(buttonWidth, buttonHeight, "Attack", null, 4);
        startFightingStage.setVisible(false);
        startFightingStage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startFightingStage.setVisible(false);
                endTurn.setVisible(true);
                game.setFightingStage(true);
                System.out.println("Starting the fighting stage!");
            }
        });
        changeButtons();
    }

    private void changeButtons() {
        setColorId(colorId*(-1));
        buttonColors(colorId);
        buyShortRangeTower.setIcon(resizeIcon(new ImageIcon(getShortRangeL1Left())));
        buyLongRangeTower.setIcon(resizeIcon(new ImageIcon(getLongRangeL1Left())));
        buySplashTower.setIcon(resizeIcon(new ImageIcon(getSplashL1Left())));
        trainSword.setIcon(resizeIcon(new ImageIcon(getSwordLeftStop())));
        trainMag.setIcon(resizeIcon(new ImageIcon(getMagLeftStop())));
    }

    private void buttonColors(int n){
        Color RED = new Color(255, 105, 105);
        Color BLUE = new Color(111, 196, 255);
        if (n == 2){
            buyShortRangeTower.setBackground(RED);
            buyLongRangeTower.setBackground(RED);
            buySplashTower.setBackground(RED);
            trainSword.setBackground(RED);
            trainMag.setBackground(RED);
        }
        if (n == -2){
            buyShortRangeTower.setBackground(BLUE);
            buyLongRangeTower.setBackground(BLUE);
            buySplashTower.setBackground(BLUE);
            trainSword.setBackground(BLUE);
            trainMag.setBackground(BLUE);
        }
    }

    /**
     * resizes the icon width to 30 and height to 35 so that it fits the button
     *
     * @param icon takes an icon to be resizes
     * @return resized Icon
     */
    private Icon resizeIcon(ImageIcon icon) {
        return resizeIcon(icon,30,35);
    }

    public void setButtonsEnabled(boolean b){
        buyShortRangeTower.setEnabled(b);
        buyLongRangeTower.setEnabled(b);
        buySplashTower.setEnabled(b);
        trainSword.setEnabled(b);
        trainMag.setEnabled(b);
        endTurn.setEnabled(b);
    }

    public void enableRightSidePanel(boolean b){
        setButtonsEnabled(!b);
    }

    @Override
    public void actionPerformed (ActionEvent e){
         updateStatusLabels();
         enableRightSidePanel(game.isFightingStage());
    }

}
