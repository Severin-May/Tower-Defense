package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(int width, int height, String text, Icon icon, int id){
        setPreferredSize(new Dimension(width, height));
        setText(text);
        setIcon(icon);

        if (id == 1){
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
            setBackground(new Color(255, 185, 94));
        }
        else if (id == 2 || id == -2){
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        }
        if (id == 4){
            setBackground(new Color(106, 255, 103));
        }
        setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), new EtchedBorder(EtchedBorder.LOWERED)));
    }
}
