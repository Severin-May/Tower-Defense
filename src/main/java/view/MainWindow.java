package view;

import model.AI;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameSettings.blueSplashL1Right;

public class  MainWindow implements KeyListener {

    private final JFrame frame;

    private final JPanel mainPanel;
    private final JPanel playerPanel;
    private JPanel playerSetup;

    private JTextField player1name, player2name;

    private boolean singleplayer = false;

    private int currentScreen = 0;

    public MainWindow() {
        frame = new JFrame("Tower Defense - Bumblebytes");

        Image icon = Toolkit.getDefaultToolkit().getImage(blueSplashL1Right);
        frame.setIconImage(icon);

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 0, frame.getHeight() / 6));

        String[] mainMenuButtons = {"START", "RULES", "EXIT"};
        for (String button : mainMenuButtons) {
            JButton btn = new JButton(button);
            // TODO: change the sizes of the buttons later
            btn.addActionListener(action);
            mainPanel.add(btn);
        }

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1, 0, frame.getHeight() / 3));

        String[] playerButtons = {"1-PLAYER", "2-PLAYER"};
        for (String button : playerButtons) {
            JButton btn = new JButton(button);
            btn.addActionListener(action);
            playerPanel.add(btn);
        }

        frame.setFocusable(true);
        frame.addKeyListener(this);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
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
        mainPanel.setVisible(false);
        playerPanel.setVisible(true);
        frame.add(playerPanel);
    }

    public void showRules() {
        JOptionPane.showMessageDialog(frame, "RULES OF THE GAME:\nIf you are struggling to beat\nyour opponent, just get better.");
    }

    public void playerMode(int n) {
        playerSetup = new JPanel();
        playerSetup.setLayout(new GridLayout(2, 1));
        JPanel names = new JPanel();
        names.setLayout(new BorderLayout());

        player1name = new JTextField(20);
        player2name = new JTextField(20);

        JButton submit = new JButton("SUBMIT");

        playerPanel.setVisible(false);
        if (n == 1) {
            names.add(player1name, BorderLayout.CENTER);
            singleplayer = true;
        } else if (n == 2) {
            names.add(player1name, BorderLayout.WEST);
            names.add(player2name, BorderLayout.EAST);
        }

        currentScreen=2;
        submit.addActionListener(action);
        playerSetup.add(names);
        playerSetup.add(submit);
        playerSetup.setVisible(true);
        frame.add(playerSetup);
    }

    public void launchGame() {
        if (singleplayer) {
            new GameWindow(new Player(player1name.getText()), new AI("The_Destroyer"));
        } else {
            new GameWindow(new Player(player1name.getText()), new Player(player2name.getText()));
        }
        frame.setVisible(false);
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
                break;
            case 1:
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
            System.out.println("ESC");
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