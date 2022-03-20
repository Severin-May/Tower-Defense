package view;

import model.Map;

import javax.swing.*;
import java.awt.*;

import static utils.GameSettings.*;

public class MapPanel extends JPanel implements Runnable{
    private final Map map = Map.getInstance(); //getInstanceLoadsImages
    public MapPanel(){
        load();
        setPreferredSize(new Dimension(mapWidthInPixels, mapHeightInPixels));
        Thread thread = new Thread(this);
        thread.start();
    }


    private void load(){

    }
    @Override
    public void paintComponent(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        map.drawMap(g);
    }
    @Override
    public void run (){
        while (true){
            repaint();
            try {
                Thread.sleep(1000L/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
