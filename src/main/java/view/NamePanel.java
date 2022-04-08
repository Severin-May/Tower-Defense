package view;

import javax.swing.*;
import java.awt.*;

public class NamePanel extends JPanel {
    private final JTextField name;

    public NamePanel (String l){
        JLabel label = new JLabel(l);
        label.setFont(new Font(Font.DIALOG, Font.ITALIC, 25));

        name = new JTextField(20);

        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 5));
        add(label);
        add(name);
        setPreferredSize(new Dimension(250, 80));
    }

    public JTextField getNameField(){
        return name;
    }
}
