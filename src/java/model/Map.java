package model;

import java.util.ArrayList;

public class Map { //extends JPanel
    private static Map instance = null;
    private int width;
    private int height;
    private ArrayList<ArrayList<Cell>> map;

    public void generateTreasure(){

    }

    private Map(int width, int height, ArrayList<ArrayList<Cell>> map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    static public void initialise (int width, int height, ArrayList<ArrayList<Cell>> map){
        if (instance == null){
            instance = new Map(width, height, map);
        }
    }

    static public Map getInstance (){
        return instance;
    }
}
