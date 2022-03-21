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
        setTitle("Game Window title");
        setSize(mapWidthInPixels+rightPanelWidth, mapHeightInPixels+30); // I do not know why +30 is needed : Jeenbek

        Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/images/Red/Buildings/Splash/L1/Left.png");
        setIconImage(icon);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//int x, int y, Image image, Player owne
        loadResources(p1,p2);
        setLayout(new BorderLayout()); //Original
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(), BorderLayout.EAST);
        setVisible(true);
    }

    private void loadResources(Player p1, Player p2) {
        Game.initialise(p1,p2);
        Map.initialise();
        p1.setCastle(new Castle(0,0, new ImageIcon("src/resources/images/Blue/Buildings/Castle/Blue.png").getImage(), p1));
        p2.setCastle(new Castle(mapWidthInPixels-cellWidth,mapHeightInPixels-cellHeight, new ImageIcon("src/resources/images/Red/Buildings/Castle/Red.png").getImage(), p2));
    }
}
