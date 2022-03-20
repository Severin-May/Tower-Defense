package model;


import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class Map {
    private static Map instance = null;
    private Cell[][] map;

    private Map (){
        map = new Cell[mapWidthInCells][mapHeightInCells];
        for (int i = 0; i < mapWidthInCells; i++){
            for (int j = 0; j < mapHeightInCells; j++){
                Image grassImage = new ImageIcon("src/resources/images/Area/Grass"+getRandomImageID()+".png").getImage();
                map[i][j] = new Cell(i*cellWidth,j*cellHeight, grassImage);
            }
        }
    }
    public void drawMap(Graphics g){
        for (int i = 0; i < mapWidthInCells; i++){
            for (int j = 0; j < mapHeightInCells; j++){
                map[i][j].drawCell(g);
            }
        }
    }

    public void generateTreasure(){

    }
    static private void initialise (){
        if (instance == null){
            instance = new Map();
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    static public Map getInstance (){
        if (instance == null){
            initialise();
        }
        return instance;
    }
    private int getRandomImageID(){
        return (int) ((Math.random() * (8 - 1)) + 1);
    }
}
