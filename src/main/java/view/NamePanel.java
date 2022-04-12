package view;

import javax.swing.*;
import java.awt.*;

public class NamePanel extends JPanel {
    private final JTextField name;

    public NamePanel (String l){
        JLabel label = new JLabel(l);
        label.setFont(new Font(Font.DIALOG, Font.ITALIC, 25));

        name = new JTextField(15);
        name.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        name.setHorizontalAlignment(JTextField.CENTER);

        setBackground(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 5));
        add(label);
        add(name);
        setPreferredSize(new Dimension(350, 80));
    }

    public JTextField getNameField(){
        return name;
    }
}
