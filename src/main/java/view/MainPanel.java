package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    public MainPanel(ActionListener action){

        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

        String[] mainMenuButtons = {"START", "RULES", "EXIT"};
        for (String button : mainMenuButtons) {
            CustomButton btn = new CustomButton(225, 80, button, null);
            btn.addActionListener(action);
            add(btn);
        }

        setPreferredSize(new Dimension(800, 40));
    }
}
