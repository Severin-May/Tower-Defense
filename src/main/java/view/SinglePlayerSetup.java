package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SinglePlayerSetup extends SetupPanel{
    public SinglePlayerSetup(ActionListener action) {
        super(action);
        NamePanel namePanel = new NamePanel("Player-1 Name:");

        CustomButton submit = new CustomButton(250, 80, "SUBMIT", null);
        submit.addActionListener(action);

        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 30));
        add(namePanel);
        add(submit);

        setPreferredSize(new Dimension(800, 60));

        setPlayerOneName(namePanel.getNameField());
    }
}
