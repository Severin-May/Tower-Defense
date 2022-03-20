package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class GameWindow extends JFrame {

    public GameWindow(Player p1, Player p2) {
        setTitle("Game Window title");
        setSize(mapWidthInPixels+rightPanelWidth, mapHeightInPixels+30); // I do not know why +30 is needed : Jeenbek
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialiseMap();
    }

    private void initialiseMap() {
        setLayout(new BorderLayout()); //Original
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(),BorderLayout.EAST);
        setVisible(true);
    }
}
