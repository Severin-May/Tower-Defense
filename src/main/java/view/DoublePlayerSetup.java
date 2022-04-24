package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DoublePlayerSetup extends SetupPanel{
    public DoublePlayerSetup(ActionListener action) {
        super(action);
        JPanel namePanels = new JPanel();
        namePanels.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        namePanels.setBackground(null);

        NamePanel namePanelOne = new NamePanel("Player-1 Name:");
        NamePanel namePanelTwo = new NamePanel("Player-2 Name:");

        namePanels.add(namePanelOne);
        namePanels.add(namePanelTwo);

        CustomButton submit = new CustomButton(250, 80, "SUBMIT", null, 1);
        submit.addActionListener(action);

        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 30));
        add(namePanels);
        add(submit);

        setBackground(null);

        setPreferredSize(new Dimension(800, 237));
        setSize(new Dimension(800, 237));

        setPlayerOneName(namePanelOne.getNameField());
        setPlayerTwoName(namePanelTwo.getNameField());
    }
}
