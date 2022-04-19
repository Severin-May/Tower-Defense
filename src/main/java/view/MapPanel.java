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
                Cell clickedCell = map.getCellFor(x, y);
                if (clickedCell != null) {
                    //if left-click on the cell
                    if (me.getButton() == MouseEvent.BUTTON1) {
                        //if trying to choose a cell to build on and the chosen place is valid
                        if (game.isPlacingBuilding() && isValidPlaceToBuild(clickedCell)){
                            //take the building from the mouse pointer and place on the chosen cell
                            putBuilding(clickedCell, game.getBuildingHover());
                            // turn off hover after clicking => remove the building from the mouse pointer
                            game.setBuildingHover(null);
                            return;
                        }
                        //set the selected cell to clicked cell if the click is not building hover click
                        Game.getInstance().setSelectedCell(clickedCell);
                    } else if (me.getButton() == MouseEvent.BUTTON3) { // if right-click on the cell
                        if (!(clickedCell.getBuilding() instanceof Tower)){
                            //do nothing on right-click if it is not a tower. (null instanceof Tower also returns false => works for empty cells)
                            return;
                        }
                        Tower towerToUpgrade = ((Tower) clickedCell.getBuilding());
                        if (canBeUpgraded(towerToUpgrade)){
                            towerToUpgrade.upgrade();
                        }
                    }
                } else {
                    // else (if the padding part was clicked), remove cell selection, remove building from pointer
                    Game.getInstance().setSelectedCell(null);
                    Game.getInstance().setBuildingHover(null);
                }

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

    /**
     * checks if given tower can be upgraded
     * @param towerToUpgrade tower to check
     * @return true if it can be upgraded. Otherwise, shows a message and returns false
     */
    private boolean canBeUpgraded(Tower towerToUpgrade) {
        if (towerToUpgrade.getOwner().getGold() < towerUpgradeCost) {
            JOptionPane.showMessageDialog(getParent(), "You are too poor to upgrade the tower. Tower upgrade cost: " + towerUpgradeCost + " golds",
                    null, JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (towerToUpgrade.isUpgraded()){
            JOptionPane.showMessageDialog(getParent(), "This tower is already upgraded",
                    null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon(towerToUpgrade.getImage())));
            return false;
        }
        return true;
    }

    /**
     * checks if given cell is a valid place to build according to game rules
     * @param cell cell to check for validity
     * @return true if valid. Otherwise, shows a message and returns false
     */
    private boolean isValidPlaceToBuild(Cell cell) {
        //Note: Money validation is done on button listeners
        if (!cell.isFreeCell()){
            JOptionPane.showMessageDialog(getParent(), "Seems like you want to build on top of someone's head or roof🤔" +
                    "\nMaybe choose a free cell?");
            return false;
        }
        if (!cell.isCloseToOwnBuilding()){
            JOptionPane.showMessageDialog(getParent(), "Oh, it might not be obvious, but according to rules you cannot build too far away from your own buildings." +
                            "\n It can be maximum 2 cells away from your own building",
                    null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon("src/main/resources/images/allowed_to_build_radius.jpg")));
            return false;
        }
        if (!cell.isInEnemyBuildingRange()){
            JOptionPane.showMessageDialog(getParent(), "I know you might be confused, but you cannot build too close to enemy building. " +
                            "\nIt should be at least 3 cells away from the enemy building",
                    null, JOptionPane.INFORMATION_MESSAGE, resizeIcon(new ImageIcon("src/main/resources/images/not_allowed_to_build_near_enemy_radius.jpg")));
            return false;
        }
        return true;
    }

    private void drawHoverBuilding(Graphics g, int x, int y, Building b) {
        g.drawImage(b.getImage(), x - b.getWidth() / 2, y - b.getHeight() / 2, b.getWidth(), b.getHeight(), null);
    }

    private void drawSelectedCellInfo(Graphics g, Cell selectedCell) {
        if (selectedCell == null){
            return;
        }
        int [] player1TroopsCount = selectedCell.getPlayer1TroopsCount();
        int [] player2TroopsCount = selectedCell.getPlayer2TroopsCount();
        char[] player1SwordCount= String.valueOf(player1TroopsCount[0]).toCharArray();
        char[] player2SwordCount= String.valueOf(player2TroopsCount[0]).toCharArray();
        char[] player1MagCount= String.valueOf(player1TroopsCount[1]).toCharArray();
        char[] player2MagCount= String.valueOf(player2TroopsCount[1]).toCharArray();
        int desiredWidth = 30;
        int desiredHeight = 45;
        //red's troop info:
        g.setColor(Color.red);
        g.drawImage(resizeIcon(new ImageIcon(redSwordLeftStop), desiredWidth,desiredHeight).getImage(),  mapWidthInPixels/2, mapHeightInPixels,null);
        g.drawChars(player1SwordCount, 0, player1SwordCount.length, mapWidthInPixels/2 + desiredWidth, mapHeightInPixels + desiredHeight - desiredHeight/2);
        g.drawImage(resizeIcon(new ImageIcon(redMagLeftStop), desiredWidth,desiredHeight).getImage(),  mapWidthInPixels/2 , mapHeightInPixels + desiredHeight,null);
        g.drawChars(player1MagCount, 0, player1MagCount.length, mapWidthInPixels/2 + desiredWidth, mapHeightInPixels + 2*desiredHeight-desiredHeight/2);
        //blue's troop info:
        g.setColor(Color.blue);
        g.drawImage(resizeIcon(new ImageIcon(blueSwordLeftStop), desiredWidth,desiredHeight).getImage(),  mapWidthInPixels/2 - desiredWidth*2, mapHeightInPixels,null);
        g.drawChars(player2SwordCount, 0, player2SwordCount.length, mapWidthInPixels/2 - desiredWidth, mapHeightInPixels + desiredHeight - desiredHeight/2);
        g.drawImage(resizeIcon(new ImageIcon(blueMagLeftStop), desiredWidth,desiredHeight).getImage(),  mapWidthInPixels/2 - desiredWidth*2 , mapHeightInPixels + desiredHeight,null);
        g.drawChars(player2MagCount, 0, player2MagCount.length, mapWidthInPixels/2 - desiredWidth, mapHeightInPixels + 2*desiredHeight-desiredHeight/2);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        map.drawMap(g);
        if (game.isPlacingBuilding()) {
            drawHoverBuilding(g, mousePointX, mousePointY, Game.getInstance().getBuildingHover());
        }
        drawSelectedCellInfo(g, Game.getInstance().getSelectedCell());
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
    private ImageIcon resizeIcon(ImageIcon icon) {
        return resizeIcon(icon, 100, 100);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int desiredWidth, int desiredHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(desiredWidth, desiredWidth, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Puts the given building on top of given cell
     * If it is a Tower or a GoldMine then adds them to its owner's list
     */
    private void putBuilding(Cell cellToBuildOn, Building toBuild) {
        //place it on the cell
        cellToBuildOn.setBuilding(toBuild);
        //if it is the tower or gold mine add it to the owner's belongings list. Note: Money validation is done on buttons
        if (toBuild instanceof Tower) {
            Tower towerToBuild = (Tower) toBuild;
            towerToBuild.getOwner().addTower(towerToBuild);
            towerToBuild.getOwner().decreaseGold(towerToBuild.getCost());
        } else if (toBuild instanceof GoldMine) {
            GoldMine goldMineToBuild = (GoldMine) toBuild;
            goldMineToBuild.getOwner().addGoldMine(goldMineToBuild);
            goldMineToBuild.getOwner().decreaseGold(goldMineToBuild.getCost());
        }
    }


}
