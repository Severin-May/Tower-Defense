package view;

import java.awt.*;
import java.awt.event.ActionListener;

public class SinglePlayerSetup extends SetupPanel{
    public SinglePlayerSetup(ActionListener action) {
        super(action);
        NamePanel namePanel = new NamePanel("Player-1 Name:");
        namePanel.setBackground(null);

        CustomButton submit = new CustomButton(250, 80, "SUBMIT", null, 1);
        submit.addActionListener(action);

        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 30));
        add(namePanel);
        add(submit);

        setBackground(null);
        setPreferredSize(new Dimension(800, 237));
        setSize(new Dimension(800, 237));

        setPlayerOneName(namePanel.getNameField());
    }
}
