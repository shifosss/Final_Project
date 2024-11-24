package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;

public class CustomAlcoholOptionPanel extends JPanel {
    private final ButtonGroup alcoholGroup = new ButtonGroup();

    public CustomAlcoholOptionPanel() {
        setLayout(new FlowLayout());
        setBackground(new Color(255, 165, 0));

        JCheckBox isAlcoholic = createStyledCheckbox("Is Alcoholic");
        JCheckBox nonAlcoholic = createStyledCheckbox("Non Alcoholic");
        JCheckBox optionalAlcoholic = createStyledCheckbox("Optional Alcoholic");

        alcoholGroup.add(isAlcoholic);
        alcoholGroup.add(nonAlcoholic);
        alcoholGroup.add(optionalAlcoholic);

        add(isAlcoholic);
        add(nonAlcoholic);
        add(optionalAlcoholic);
    }

    private JCheckBox createStyledCheckbox(String label) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setBackground(new Color(255, 165, 0));
        checkBox.setForeground(Color.WHITE);
        checkBox.setFont(new Font("SansSerif", Font.BOLD, 14));
        return checkBox;
    }
}
