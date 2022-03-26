package model;


import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class Map {
    private static Map instance = null;
    private Cell[][] map;

    private Map (){
        map = new Cell[mapHeightInCells][mapWidthInCells];
        for (int i = 0; i < mapHeightInCells; i++){
            for (int j = 0; j < mapWidthInCells; j++){
                Image grassImage = new ImageIcon("src/resources/images/Area/Grass"+getRandomImageID()+".png").getImage();
                map[i][j] = new Cell(j*cellHeight,i*cellWidth, grassImage);
            }
        }
    }
    public void drawMap(Graphics g){
        for (int i = 0; i < mapHeightInCells; i++){
            for (int j = 0; j < mapWidthInCells; j++){
                map[i][j].drawCell(g);
            }
        }
    }

    public void generateTreasure(){

    }
    public static void initialise (){
        if (instance == null){
            instance = new Map();
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    static public Map getInstance (){
        return instance;
    }
    private int getRandomImageID(){
        return (int) ((Math.random() * (8 - 1)) + 1);
    }
}
