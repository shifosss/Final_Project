package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that contains radio buttons to choose for the alcohol state.
 */
public class AlcoholicPanel extends JPanel {
    private final ButtonGroup group;

    public AlcoholicPanel() {
        setLayout(new GridLayout(3, 1));
        group = new ButtonGroup();
        final JRadioButton option1 = new JRadioButton("Alcoholic");
        final JRadioButton option2 = new JRadioButton("Non alcoholic");
        final JRadioButton option3 = new JRadioButton("Optional alcohol");

        group.add(option1);
        group.add(option2);
        group.add(option3);

        add(option1);
        add(option2);
        add(option3);
    }

    public String getSelectedOption() {
        return group.getSelection() != null ? group.getSelection().getActionCommand() : null;
    }
}
