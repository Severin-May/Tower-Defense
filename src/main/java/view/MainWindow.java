package view;

import model.AI;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameSettings.blueSplashL1Right;

public class MainWindow extends JFrame implements KeyListener {

    private final MainPanel mainPanel;
    private final PlayerPanel playerPanel;
    private SetupPanel playerSetup;

    private JLabel help;

    private boolean singleplayer = false;

    private int currentScreen = 0;

    public MainWindow() {
        setTitle("Tower Defense - Bumblebytes");

        Image icon = Toolkit.getDefaultToolkit().getImage(blueSplashL1Right);
        setIconImage(icon);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new MainPanel(action);
        setBackground(Color.black);

        playerPanel = new PlayerPanel(action);

        add(panelToDisplay(mainPanel));

        setResizable(false);
        setFocusable(true);
        addKeyListener(this);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private final ActionListener action = e -> {
        JButton btn = (JButton) (e.getSource());
        switch (btn.getText()) {
            case "START":
                start();
                break;
            case "RULES":
                showRules();
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
        currentScreen=1;
        help = new JLabel("Press 'ESC' to go back");
        add(help, BorderLayout.NORTH);
        add(changePanel(playerPanel, mainPanel));
    }

   public void showRules() {
       JOptionPane.showMessageDialog(this, "RULES OF THE GAME:\nIf you are struggling to beat\nyour opponent, just get better.");
   }

    public void playerMode(int n) {
        if (n == 1) {
            playerSetup = new SinglePlayerSetup(action);
            singleplayer = true;
        } else if (n == 2) {
            playerSetup = new DoublePlayerSetup(action);
        }
        currentScreen=2;
        add(changePanel(playerSetup, playerPanel));
    }

    public void launchGame() {
        if (singleplayer) {
            new GameWindow(new Player(playerSetup.getPlayerOneName().getText()), new AI("The_Destroyer"));
        } else {
            new GameWindow(new Player(playerSetup.getPlayerOneName().getText()), new Player(playerSetup.getPlayerTwoName().getText()));
        }
        dispose();
    }

    /*private JPanel panelToDisplay(JPanel newPanel){

        JPanel hold = new JPanel();
        hold.setLayout(new GridLayout(1,1));
        hold.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        newPanel.setBackground(Color.cyan);
        hold.add(newPanel, SwingConstants.CENTER);
        return hold;
    }

    private JPanel changePanel(JPanel newPanel, JPanel oldPanel){
        newPanel.setVisible(true);
        oldPanel.setVisible(false);
        return panelToDisplay(newPanel);
    }*/

    private Box panelToDisplay(JPanel newPanel){

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(newPanel);
        box.add(Box.createVerticalGlue());
        return box;
    }

    private Box changePanel(JPanel newPanel, JPanel oldPanel){
        newPanel.setVisible(true);
        oldPanel.setVisible(false);
        return panelToDisplay(newPanel);
    }

    /**
     * allows switching between menu screens
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

    @Override
    public void keyPressed (KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (currentScreen > 0){
                currentScreen--;
            }
            display(currentScreen);
        }
    }
    @Override
    public void keyReleased (KeyEvent ke) {
    }
    @Override
    public void keyTyped (KeyEvent ke) {
    }
}