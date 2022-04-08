package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(int width, int height, String text, Icon icon, int id){
        this.setPreferredSize(new Dimension(width, height));
        this.setText(text);
        this.setIcon(icon);

        if (id == 1){
            this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
            this.setBackground(new Color(255, 185, 94));
        }
        else if (id > 1){
            this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        }
        if (id == 4){
            this.setBackground(new Color(106, 255, 103));
        }
        this.setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), new EtchedBorder(EtchedBorder.LOWERED)));
    }
}
