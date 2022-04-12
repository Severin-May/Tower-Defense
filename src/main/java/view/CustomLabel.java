package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import static utils.GameSettings.*;

public class CustomLabel extends JLabel {
    public CustomLabel(){
        setPreferredSize(new Dimension(rightPanelWidth/2, 30));
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        setBorder(new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED), new EtchedBorder(EtchedBorder.LOWERED)));
    }
}
