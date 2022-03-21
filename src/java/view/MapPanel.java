package view;

import model.Building;
import model.Cell;
import model.Game;
import model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utils.GameSettings.*;

public class MapPanel extends JPanel implements Runnable{
    private final Map map;
    private final Game game;
    private int mousePointX;
    private int mousePointY;
    public MapPanel(){
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
                Cell cellToClick = getCellFor(x,y);
                if (cellToClick != null)
                    if(me.getButton() == MouseEvent.BUTTON1) {
                        cellToClick.click();
                    }
                    else if(me.getButton() == MouseEvent.BUTTON3) {
                    cellToClick.rightClick();
                }

                System.out.println(me.getButton() == MouseEvent.BUTTON3);
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
     * Takes screen pixel x and y as parameters and returns cell that is located there
     * @param x x coordinate which will be converted to j index
     * @param y y coordinate which will be converted to i index
     * @return returns Cell that is located there. Returns null for invalid coordinates
     */
    private Cell getCellFor(int x, int y){
        if (x > mapWidthInPixels || x < 0 || y > mapHeightInPixels || y < 0){
            return null;
        }
        return map.getMap()[y/cellHeight][x/cellWidth];
    }
    private void drawHoverBuilding(Graphics g, int x, int y, Building b){
        g.drawImage(b.getImage(), x-cellWidth/2, y-cellHeight/2, cellWidth, cellHeight,null);
    }
    @Override
    public void paintComponent(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        map.drawMap(g);
        if (game.isPlacingTower()){
            drawHoverBuilding(g,mousePointX,mousePointY,Game.getInstance().getBuildingHover()); //temporarily now it drags only shortRange
        }
    }
    @Override
    public void run (){
        while (!Game.getInstance().isGameEnded()){
            repaint();
            try {
                Thread.sleep(1000L/fps);
            } catch (InterruptedException ignored){}
        }
    }


}
