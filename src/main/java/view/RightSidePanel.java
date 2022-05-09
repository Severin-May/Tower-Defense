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

/**
 * Right side panel is expected to be on the right of the whole parent JFrame
 * It wraps currents status,
 */
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
    CustomButton trainSpecUnit;
    CustomButton buyGoldMine;
    CustomButton endTurn;
    CustomButton startFightingStage;

    /**
     * This panel uses {@link BorderLayout} and divides the panel into 3 sections.
     * It puts:
     * the current player's label {@link #playerName} on BorderLayout.NORTH,
     * the {@link StatusPanel} on BorderLayout.CENTER,
     * the {@link ShopPanel} on BorderLayout.SOUTH
     * It is important to know that it starts a timer that updates everything in this panel according to current state every interval time set by {@link #timer}
     */
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
        add(new ShopPanel(buyShortRangeTower, buyLongRangeTower, buySplashTower, trainSword, trainMag, trainSpecUnit, buyGoldMine, endTurn, startFightingStage), BorderLayout.SOUTH);
        timer = new Timer(500, this);// status panel update timer
        timer.start();
    }


    /**
     * Creates all the necessary current status text labels
     */
    private void createLabels() {
        gold = new CustomLabel();
        troopsTrained = new CustomLabel();
        goldMines = new CustomLabel();
        towers = new CustomLabel();
        castleHp = new CustomLabel();
    }

    /**
     * Updates the text of all the labels according to current state
     * Can be call only if labels are created first using {@link #createLabels()}
     */
    private void updateStatusLabels() {
        playerName.setText("Current Player: " + game.getCurrentTurn().getName());
        gold.setText("GOLD: " + game.getCurrentTurn().getGold());
        troopsTrained.setText("Units on the field: " + game.getCurrentTurn().getTroops().size());
        goldMines.setText("Gold mines built: " + game.getCurrentTurn().getGoldMines().size());
        towers.setText("Towers built: " + game.getCurrentTurn().getTowers().size());
        castleHp.setText("Your Castle HP: " + game.getCurrentTurn().getCastle().getHealthPoints());
    }

    /**
     * Creates all the necessary control buttons with the action listeners implemented
     */
    private void createButtons() {
        int buttonWidth = 180;
        int buttonHeight = 55;
        int colorId = -2;
        buyShortRangeTower = new CustomButton(buttonWidth, buttonHeight, "Short Range", resizeIcon(new ImageIcon(getShortRangeL1Left())), colorId);
        buyShortRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.getCurrentTurn().getGold() < shortRangeCost) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Oops. Looks like you are too poor to purchase short ranged tower. It costs " + shortRangeCost + " golds",
                            "Not enough gold",
                            JOptionPane.INFORMATION_MESSAGE,
                            resizeIcon(new ImageIcon(getShortRangeL1Left())));
                    return;
                }
                game.setBuildingHover(new ShortRange(game.getCurrentTurn()));
            }
        });
        buyLongRangeTower = new CustomButton(buttonWidth, buttonHeight, "Long Range", resizeIcon(new ImageIcon(getLongRangeL1Left())), colorId);
        buyLongRangeTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.getCurrentTurn().getGold() < longRangeCost) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Oops. Looks like you are too poor to purchase long ranged tower. It costs " + longRangeCost + " golds",
                            "Not enough gold",
                            JOptionPane.INFORMATION_MESSAGE,
                            resizeIcon(new ImageIcon(getLongRangeL1Left())));
                    return;
                }
                game.setBuildingHover(new LongRange(game.getCurrentTurn()));
            }
        });
        buySplashTower = new CustomButton(buttonWidth, buttonHeight, "Splash", resizeIcon(new ImageIcon(getSplashL1Left())), colorId);
        buySplashTower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.getCurrentTurn().getGold() < splashCost) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Oops. Looks like you are too poor to purchase splash tower. It costs " + splashCost + " golds",
                            "Not enough gold",
                            JOptionPane.INFORMATION_MESSAGE,
                            resizeIcon(new ImageIcon(getSplashL1Left())));
                    return;
                }
                game.setBuildingHover(new Splash(game.getCurrentTurn()));
            }
        });
        trainSword = new CustomButton(buttonWidth, buttonHeight * 3 / 2, "Barbarian", resizeIcon(new ImageIcon(getSwordLeftStop())), colorId);
        trainSword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!game.getCurrentTurn().buyTroop(TroopType.SWORD_MAN)) {
                    JOptionPane.showMessageDialog(getParent(), "Oops. Looks like you are too poor to purchase more sword men");
                }
            }
        });
        trainMag = new CustomButton(buttonWidth, buttonHeight * 3 / 2, "Wizard", resizeIcon(new ImageIcon(getMagLeftStop())), colorId);
        trainMag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!game.getCurrentTurn().buyTroop(TroopType.WIZ)) {
                    JOptionPane.showMessageDialog(getParent(), "Oops. Looks like you are too poor to purchase more magicians");
                }
            }
        });

        trainSpecUnit = new CustomButton(buttonWidth, buttonHeight * 3 / 2, "Ghost", resizeIcon(new ImageIcon(getSpecialLeftStop())), colorId);
        trainSpecUnit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //todo comeback later
                if (!game.getCurrentTurn().buyTroop(TroopType.SPECIAL_UNIT)) {
                    JOptionPane.showMessageDialog(getParent(), "Oops. Looks like you are too poor to purchase more wizards");
                }
            }
        });

        buyGoldMine = new CustomButton(buttonWidth, buttonHeight * 3 / 2, "Gold", resizeIcon(new ImageIcon(getGoldMine())), colorId);
        buyGoldMine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.getCurrentTurn().getGold() < goldMineCost) {
                    JOptionPane.showMessageDialog(getParent(),
                            "Oops. Looks like you are too poor to purchase a gold mine. It costs " + goldMineCost + " golds",
                            "Not enough gold",
                            JOptionPane.INFORMATION_MESSAGE,
                            resizeIcon(new ImageIcon(getGoldMine())));
                    return;
                }
                game.setBuildingHover(new GoldMine(game.getCurrentTurn()));
            }
        });
        endTurn = new CustomButton(buttonWidth, buttonHeight, "End Turn", null, 4);
        endTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endTurn.setVisible(false);
                startFightingStage.setVisible(true);
                game.changeTurn();
                //if after changing turn it is AI's turn, then do preparations and start immediately
                if (game.isSinglePlayer() && game.getCurrentTurn() == game.getPlayer2()) {
                    AI ai = (AI) game.getPlayer2();
                    ai.doPreparations();
                    ai.clickOnAttack();
                }
            }
        });
        startFightingStage = new CustomButton(buttonWidth, buttonHeight, "Attack", null, 4);
        startFightingStage.setVisible(false);
        startFightingStage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startFightingStage.setVisible(false);
                disableAllButtons();
                endTurn.setVisible(true);
                game.setFightingStage(true);
                System.out.println("Starting the fighting stage!");
            }
        });
        if (game.isSinglePlayer()) {
            ((AI) game.getPlayer2()).setControlButtons(endTurn, startFightingStage); // temporarily
        }
    }
    
    private void disableAllButtons(){
        buyShortRangeTower.setEnabled(false);
        buySplashTower.setEnabled(false);
        buyLongRangeTower.setEnabled(false);
        buyGoldMine.setEnabled(false);
        
        trainMag.setEnabled(false);
        trainSpecUnit.setEnabled(false);
        trainSword.setEnabled(false);
        
        endTurn.setEnabled(false);
    }

    /**
     * update buttons' icons and border color according to whose turn it is
     * Can be called only if buttons are created first using {@link #createButtons()}
     */
    private void updateButtons() {
        // update buttons icons
        buyShortRangeTower.setIcon(resizeIcon(new ImageIcon(getShortRangeL1Left())));
        buyLongRangeTower.setIcon(resizeIcon(new ImageIcon(getLongRangeL1Left())));
        buySplashTower.setIcon(resizeIcon(new ImageIcon(getSplashL1Left())));
        trainSword.setIcon(resizeIcon(new ImageIcon(getSwordLeftStop())));
        trainMag.setIcon(resizeIcon(new ImageIcon(getMagLeftStop())));
        buyGoldMine.setIcon(resizeIcon(new ImageIcon(getGoldMine())));
        trainSpecUnit.setIcon(resizeIcon(new ImageIcon(getSpecialFront())));
        Color RED = new Color(255, 105, 105);
        Color BLUE = new Color(111, 196, 255);
        // update button borders
        if (game.getCurrentTurn() == game.getPlayer1()) {
            buyShortRangeTower.setBackground(RED);
            buyLongRangeTower.setBackground(RED);
            buySplashTower.setBackground(RED);
            trainSword.setBackground(RED);
            trainMag.setBackground(RED);
            trainSpecUnit.setBackground(RED);
            buyGoldMine.setBackground(RED);
        } else {
            buyShortRangeTower.setBackground(BLUE);
            buyLongRangeTower.setBackground(BLUE);
            buySplashTower.setBackground(BLUE);
            trainSword.setBackground(BLUE);
            trainMag.setBackground(BLUE);
            trainSpecUnit.setBackground(BLUE);
            buyGoldMine.setBackground(BLUE);
        }
    }


    /**
     * resizes the icon width to 30 and height to 35 so that it fits the button
     *
     * @param icon takes an icon to be resizes
     * @return resized Icon
     */
    private Icon resizeIcon(ImageIcon icon) {
        return resizeIcon(icon, 30, 35);
    }

    /**
     * resizes the icon width and height as indicated
     * @param icon an icon to be resized
     * @param desiredWidth new width
     * @param desiredHeight new height
     * @return new resized Icon
     */
    public static Icon resizeIcon(ImageIcon icon, int desiredWidth, int desiredHeight) {
        if (icon != null) {
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
        return null;
    }

    /**
     * enables/disables the buttons
     * @param b true => enable; false => disable
     */
    private void setButtonsEnabled(boolean b) {
        buyShortRangeTower.setEnabled(b);
        buyLongRangeTower.setEnabled(b);
        buySplashTower.setEnabled(b);
        trainSword.setEnabled(b);
        trainMag.setEnabled(b);
        trainSpecUnit.setEnabled(b);
        endTurn.setEnabled(b);
        buyGoldMine.setEnabled(b);
    }

    /**
     * this function is triggered by the {@link #timer} after it is set to start
     * or by any other actions that is performed on this panel
     * It updates the state of the status labels and buttons according to current state
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateStatusLabels();
        updateButtons();
        setButtonsEnabled(!game.isFightingStage());
    }
}
