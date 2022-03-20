package view;

import model.AI;
import model.Game;
import model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    private final JFrame frame;

    private final JPanel mainPanel;
    private final JPanel playerPanel;

    private JTextField player1name, player2name;

    private boolean singleplayer = false;

    public MainWindow(){
        frame = new JFrame("Tower Defense - Bumblebytes");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 0, frame.getHeight()/6));

        String[] mainMenuButtons = {"START", "RULES", "EXIT"};
        for (String button : mainMenuButtons){
            JButton btn = new JButton(button);
            btn.addActionListener(action);
            mainPanel.add(btn);
        }

        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1, 0, frame.getHeight()/3));

        String[] playerButtons = {"1-PLAYER", "2-PLAYER"};
        for (String button : playerButtons){
            JButton btn = new JButton(button);
            btn.addActionListener(action);
            playerPanel.add(btn);
        }

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private final ActionListener action = e -> {
//        JButton btn = (JButton) (e.getSource());
//        switch (btn.getText()){
//            case "START" -> start();
//            case "RULES" -> showRules();
//            case "1-PLAYER", "2-PLAYER" -> playerMode(Integer.parseInt(btn.getText().substring(0, 1)));
//            case "EXIT" -> System.exit(100);
//            case "SUBMIT" -> launchGame();
//            default -> throw new IllegalStateException("Unexpected value: " + btn.getText());
//        }
    };

    public void start(){
        mainPanel.setVisible(false);
        frame.add(playerPanel);
    }

    public void showRules(){
        JOptionPane.showMessageDialog(frame, "RULES OF THE GAME:\nIf you are struggling to beat\nyour opponent, just get better.");
    }

    public void playerMode(int n){
        JPanel playerSetup = new JPanel();
        playerSetup.setLayout(new GridLayout(2, 1));
        JPanel names = new JPanel();
        names.setLayout(new BorderLayout());

        player1name = new JTextField(20);
        player2name = new JTextField(20);

        JButton submit = new JButton("SUBMIT");

        playerPanel.setVisible(false);
        if (n == 1){
            names.add(player1name, BorderLayout.CENTER);
            singleplayer = true;
        }
        else if (n == 2){
            names.add(player1name, BorderLayout.WEST);
            names.add(player2name, BorderLayout.EAST);
        }

        submit.addActionListener(action);
        playerSetup.add(names);
        playerSetup.add(submit);
        frame.add(playerSetup);
    }

    public void launchGame(){
        if (singleplayer){
            new GameWindow(new Player(player1name.getText()), new AI("The_Destroyer"));
        } else {
            new GameWindow(new Player(player1name.getText()), new Player(player2name.getText()));
        }
        frame.setVisible(false);
    }
}