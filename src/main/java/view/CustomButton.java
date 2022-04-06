package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(int width, int height, String text, Icon icon){
        this.setPreferredSize(new Dimension(width, height));
        this.setText(text);
        this.setIcon(icon);

        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        this.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), new EtchedBorder(EtchedBorder.LOWERED)));
        this.setBackground(new Color(64, 160, 114));
    }
}
