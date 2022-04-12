package view;

import model.*;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Game Window title");
        setSize(mapWidthInPixels + rightPanelWidth, mapHeightInPixels + 200); // I do not know why +30 is needed : Jeenbek

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Blue/Buildings/Splash/L1/Right.png");
        setIconImage(icon);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(), BorderLayout.EAST);
        setVisible(true);
        setUpMenuBar();
        Game.everyThingReady.set(true);
    }

    private void setUpMenuBar(){
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        mb.add(menu);
        setJMenuBar(mb);
        JMenuItem menuGameStart = new JMenuItem(new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //restartGame();
                Game.getInstance().getPlayer1().resetPlayer();
                Game.getInstance().getPlayer2().resetPlayer();
                Map.resetMap(); //todo
            }
        });
        JMenuItem menuGameRules = new JMenuItem(new AbstractAction("Rules") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRules();
            }
        });
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuGameStart);
        menu.add(menuGameRules);
        menu.add(menuGameExit);
    }

    public void showRules() {
        JOptionPane.showMessageDialog(this, "RULES OF THE GAME:\nIf you are struggling to beat\nyour opponent, just get better.");
    }

    public void restartGame(Game g) {
        g.getPlayer1().resetPlayer();
        g.getPlayer2().resetPlayer();
    }
}
