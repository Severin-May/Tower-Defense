package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerPanel extends JPanel {

    public PlayerPanel(ActionListener action){
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 20));

        String[] playerButtons = {"1-PLAYER", "2-PLAYER"};
        for (String button : playerButtons) {
            CustomButton btn = new CustomButton(275, 100, button, null);
            btn.addActionListener(action);
            add(btn);
        }

        setPreferredSize(new Dimension(800, 10));
    }
}
