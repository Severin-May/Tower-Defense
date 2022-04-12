package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.GameSettings.*;

public class MapPanel extends JPanel implements Runnable {
    private final Map map;
    private final Game game;
    private int mousePointX;
    private int mousePointY;

    public MapPanel() {
        game = Game.getInstance();
        map = Map.getInstance();
        setPreferredSize(new Dimension(mapWidthInPixels, mapHeightInPixels));
        Thread thread = new Thread(this);
        thread.start();
        addMouseListener(new MouseAdapter() { // map listens for click
            @Override
            public void mousePressed(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                Cell cellToClick = map.getCellFor(x, y);
                if (cellToClick != null) {
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        if (game.isPlacingBuilding()){
                            if (!cellToClick.isFreeCell()){
                                JOptionPane.showMessageDialog(getParent(), "Seems like you want to build on top of someone's head or roof🤔" +
                                                                                    "\nMaybe choose a free cell?");
                                return;
                            }
                            if (!cellToClick.isCloseToOwnBuilding()){
                                JOptionPane.showMessageDialog(getParent(), "Oh, it might not be obvious, but according to rules you cannot build too far away from your own buildings." +
                                                                                    "\n It can be maximum 2 cells away from your own building",
                                                                                null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon("src/main/resources/images/allowed_to_build_radius.jpg")));
                                return;
                            }
                            if (!cellToClick.isInEnemyBuildingRange()){
                                JOptionPane.showMessageDialog(getParent(), "I know you might be confused, but you cannot build too close to enemy building. " +
                                                                                    "\nIt should be at least 3 cells away from the enemy building",
                                                                                null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon("src/main/resources/images/not_allowed_to_build_near_enemy_radius.jpg")));
                                return;
                            }
                        }
                        cellToClick.click();
                    } else if (me.getButton() == MouseEvent.BUTTON3) {
                        if (!(cellToClick.hasBuilding() && cellToClick.getBuilding() instanceof Tower && cellToClick.getBuilding().getOwner().getGold() >= towerUpgradeCost)) {
                            JOptionPane.showMessageDialog(getParent(), "You are too poor to upgrade the tower. Tower upgrade cost: " + towerUpgradeCost + " golds",
                                                     null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon("src/main/resources/images/not_allowed_to_build_near_enemy_radius.jpg")));
                            return;
                        }
                        cellToClick.rightClick();
                    }
                } // else, not the playground was clicked. Most probably padding part was
            }
        });
        addMouseMotionListener(new MouseAdapter() { // map listens and records where the mouse is pointing to
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePointX = e.getX();
                mousePointY = e.getY();
            }
        });

    }

    private void drawHoverBuilding(Graphics g, int x, int y, Building b) {
        g.drawImage(b.getImage(), x - b.getWidth() / 2, y - b.getHeight() / 2, b.getWidth(), b.getHeight(), null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        map.drawMap(g);
        if (game.isPlacingBuilding()) {
            drawHoverBuilding(g, mousePointX, mousePointY, Game.getInstance().getBuildingHover());
        }
    }

    @Override
    public void run() {
        while (!Game.gameOver.get()) {
            repaint();
            try {
                Thread.sleep(1000L / fps);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * resizes the icon width to 100 and height to 100 so that it fits message dialogue
     *
     * @param icon takes an icon to be resizes
     * @return resized Icon
     */
    private Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


}
