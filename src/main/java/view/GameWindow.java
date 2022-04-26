package view;

import model.*;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        //title of the window
        setTitle("Tower Defense");
        //icon of the window next to title
        setIconImage(blueSplashL1Right);
        //size of the window
        setSize(mapWidthInPixels + rightPanelWidth, mapHeightInPixels + additionalBlankSpaceBelowInPixels);
        //cannot be resized
        setResizable(false);
        //window appears in the center
        setLocationRelativeTo(null);
        //exits on close button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //divide screen into left and right, and add corresponding panels there
        setLayout(new BorderLayout());
        add(new MapPanel(), BorderLayout.WEST);
        add(new RightSidePanel(), BorderLayout.EAST);
        setVisible(true);
        //add a menu bar
        setUpMenuBar();
        //everything is ready so that Main can continue
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
                Game.getInstance().resetGame();
            }
        });
        JMenuItem menuGameRules = new JMenuItem(new AbstractAction("Rules") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.showRules(getParent());
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
}
