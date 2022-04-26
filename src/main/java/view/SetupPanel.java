package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class SetupPanel extends JPanel {

    private JTextField p1name, p2name;

    public SetupPanel(ActionListener action){}

    public void setPlayerOneName(JTextField name){
        p1name = name;
    }

    public void setPlayerTwoName(JTextField name){
        System.out.println("setting to "+ name.getText());
        p2name = name;
    }

    public JTextField getPlayerOneName(){
        return p1name;
    }

    public JTextField getPlayerTwoName(){
        return p2name;
    }
}
