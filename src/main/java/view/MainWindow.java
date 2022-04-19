package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameSettings.*;
import static view.RightSidePanel.resizeIcon;

public class MainWindow extends JFrame{

    private final JPanel framePanel;

    private final JPanel menusPanel;

    private final MainPanel mainPanel;
    private final PlayerPanel playerPanel;
    private SetupPanel playerSetup;

    private JLabel help;

    private boolean singleplayer = false;

    private int currentScreen = 0;

    public MainWindow() {
        setTitle("Tower Defense - Bumblebytes");

        setIconImage(blueSplashL1Right);

        framePanel = new JPanel();
        framePanel.setSize(new Dimension(800, 800));
        framePanel.setBackground(Color.cyan);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menusPanel = new JPanel();
        menusPanel.setBackground(null);

        mainPanel = new MainPanel(action);
        setBackground(Color.black);

        playerPanel = new PlayerPanel(action);

        panelToDisplay(mainPanel);

        add(framePanel);
        setResizable(false);
        setFocusable(true);
        addKeyListener(back);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private final KeyListener back = new KeyListener() {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if (currentScreen > 0) {
                    currentScreen--;
                }
                display(currentScreen);
            }
        }

        public void keyReleased(KeyEvent ke) {
        }

        public void keyTyped(KeyEvent ke) {
        }
    };

    private final ActionListener action = e -> {
        JButton btn = (JButton) (e.getSource());
        switch (btn.getText()) {
            case "START":
                start();
                break;
            case "RULES":
                showRules(getParent());
                break;
            case "1-PLAYER":
            case "2-PLAYER":
                playerMode(Integer.parseInt(btn.getText().substring(0, 1)));
                break;
            case "EXIT":
                System.exit(100);
                break;
            case "SUBMIT":
                launchGame();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getText());
        }
    };

    public void start() {
        currentScreen = 1;
        help = new JLabel("Press 'ESC' to go back");
        help.setAlignmentY(JComponent.LEFT_ALIGNMENT);
        framePanel.add(help, BorderLayout.NORTH);
        changePanel(playerPanel, mainPanel);
    }

    /**
     * Pops up a dialogue giving out the message that is passed and returning an option
     * @param message message that should be displayed in the popup
     * @param title title of the message
     * @param icon icon that appears on the side of the message pop-up
     * @param frame component frame where this message should be displayed
     * @return choice of the user. Next = 0, Enough of rules = 1, Go back = 2. Close = -1
     */
    private static int showDialogue(String message, String title, ImageIcon icon, Component frame){
        Object[] options = {"Next", "Enough of rules", "Go back"};
        return JOptionPane.showOptionDialog(frame, message, title,JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,resizeIcon(icon,100,100),options,options[0]);
    }

    public void playerMode(int n) {
        if (n == 1) {
            playerSetup = new SinglePlayerSetup(action);
            singleplayer = true;
        } else if (n == 2) {
            playerSetup = new DoublePlayerSetup(action);
        }
        currentScreen = 2;
        changePanel(playerSetup, playerPanel);
    }

    public void launchGame() {
        if (singleplayer) {
            loadGameAndMap(new Player(playerSetup.getPlayerOneName().getText()), new AI("The_Destroyer"));
        } else {
            loadGameAndMap(new Player(playerSetup.getPlayerOneName().getText()), new Player(playerSetup.getPlayerTwoName().getText()));
        }
        new GameWindow();
        dispose();
    }

    private void loadGameAndMap(Player p1, Player p2) {
        Game.initialise(p1, p2);
        Map.initialise();
        p1.setColor(Color.red);
        p2.setColor(Color.blue);
        Map.getInstance().putRandomCastles();
        Map.getInstance().putRandomObstacles();
    }

    private void panelToDisplay(JPanel newPanel){

        newPanel.setVisible(true);
        menusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, (getHeight() - newPanel.getHeight())/2));
        menusPanel.add(newPanel);
        newPanel.setBackground(null);
        //menusPanel.setBackground(Color.cyan);

        framePanel.add(menusPanel);

    }

    private void changePanel(JPanel newPanel, JPanel oldPanel) {
        oldPanel.setVisible(false);
        panelToDisplay(newPanel);
    }

    /**
     * allows switching between menu screens
     *
     * @param screen the screen to be displayed
     */
    public void display(int screen) {
        switch (screen) {
            case 0:
                mainPanel.setVisible(true);
                playerPanel.setVisible(false);
                help.setVisible(false);
                break;
            case 1:
                help.setVisible(true);
                playerPanel.setVisible(true);
                playerSetup.setVisible(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + screen);
        }
    }

    /**
     * Displays the rules
     * @param frame frame where this rule should be displayed
     */
    public static void showRules(Component frame) {
        Object[] options = {"Next", "Don't need your rules"};
        int n = JOptionPane.showOptionDialog(frame, welcomeMessage, "Know the rules well, so you can break them effectively!",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
        int page = 0;
        while (n != -1 && n != 1) {
            switch (page) {
                case 0: {
                    n = showDialogue(aboutGameGoal,"Destroy or be destroyed!",null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 1: {
                    n = showDialogue(preparationStage, "Prepare for battle!", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 2: {
                    n = showDialogue(aboutTroops, "For the motherland!", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 3: {
                    n = showDialogue(aboutTroopsAttributes, "Existing stats for troop", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 4: {
                    n = showDialogue(aboutSwordManTroop, "Sword man stats", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 5: {
                    n = showDialogue(aboutMagTroop, "Mag stats", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 6: {
                    n = showDialogue(aboutTowers, "Defend the King! Viva la Vida", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 7: {
                    n = showDialogue(aboutTowerAttributes, "Existing stats for towers", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 8: {
                    n = showDialogue(aboutTowerRadius, "Remember about stairs-like radius!", allow_to_build_radius, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 9: {
                    n = showDialogue(aboutShortRangeTower, "Short range tower stats", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 10: {
                    n = showDialogue(aboutLongRangeTower, "Long range tower stats", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }case 11: {
                    n = showDialogue(aboutSplashTower, "Splash tower stats", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 12: {
                    n = showDialogue(aboutBuildingWithinAllyBuildingRange, "Do not build too far", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 13: {
                    n = showDialogue(aboutBuildingWithinEnemyBuildingRange, "Never forget about stairs-like radius!", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 14: {
                    n = showDialogue(aboutBuildingWithoutBlockingPath, "Yo, dude, get out the way!", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 15: {
                    n = showDialogue(aboutEarningGold, "Time is money", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 16: {
                    n = showDialogue(aboutGoldMines, "Invest in your future!", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 17: {
                    n = showDialogue(aboutEarningMoneyFromTroopKills, "Killing is bad... Or is it?", null, frame);
                    if (n == 0) {
                        page++;
                    } else if (n == 2) {
                        page--;
                    }
                    break;
                }
                case 18: {
                    Object[] lastOptions = {"Go back","Go to the beginning of the rules", "Done reading"};
                    int n1 = JOptionPane.showOptionDialog(frame, "Fun fact: You skipped most part of the rules just to scroll till the end\nI don't judge you tho", "Good to go",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,lastOptions,null);
                    if (n1 == 1){
                        page = 0;
                    }else if (n1 == 0){
                        page--;
                    }else{
                        n = -1;
                    }
                    break;
                }
                default: {
                    n=-1;
                    break;
                }
            }
        }
    }
}