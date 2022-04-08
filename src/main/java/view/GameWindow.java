package view;

import model.Castle;
import model.Game;
import model.Map;
import model.Player;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class GameWindow extends JFrame {

    public GameWindow(Player p1, Player p2) {
        System.out.println(p1.getName() + " || " + p2.getName());
        setTitle("Game Window title");
        setSize(mapWidthInPixels + rightPanelWidth, mapHeightInPixels + 30); // I do not know why +30 is needed : Jeenbek

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Blue/Buildings/Splash/L1/Right.png");
        setIconImage(icon);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadResources(p1, p2);
        setLayout(new BorderLayout());
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(), BorderLayout.EAST);
        setVisible(true);
    }

    private void loadResources(Player p1, Player p2) {
        Game.initialise(p1, p2);
        Map.initialise();
        p1.setColor("Red");
        p2.setColor("Blue");
        p1.setCastle(new Castle(0, 0, p1));
        p2.setCastle(new Castle(mapHeightInCells - 1, mapWidthInCells - 1, p2));
    }
}
