package view;

import model.Game;
import model.Player;
import utils.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.GameSettings.*;

public class GameWindow extends JFrame {

    public GameWindow(Player p1, Player p2) {
//        mapPanel.setBackground(Color.PINK);

        setTitle("Game Window title");
        setSize(mapWidthInPixels+rightPanelWidth, mapHeightInPixels+30); // I do not know why +30 is needed : Jeenbek
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialiseMap();
//        add(mapPanel);
//        System.out.println(mapPanel.getWidth());
//        System.out.println(mapPanel.getHeight());
    }

    private void initialiseMap() {
        setLayout(new BorderLayout()); //Original
//        setLayout(new BorderLayout());
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(),BorderLayout.EAST);
//        add(new AllPanels());
        setVisible(true);
    }
}
