package view;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public ShopPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(new JButton("BUILD TOWER"),gbc);
        gbc.gridy = 1;
        add(new JButton("TRAIN TROOP"),gbc);
    }
}
