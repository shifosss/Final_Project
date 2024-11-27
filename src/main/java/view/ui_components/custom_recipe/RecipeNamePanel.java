package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;

public class RecipeNamePanel extends JPanel {
    private final JTextField nameField;

    public RecipeNamePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel nameLabel = new JLabel("Recipe Name:");
        nameField = new JTextField(20);

        add(nameLabel);
        add(nameField);
    }

    public String getRecipeName() {
        return nameField.getText();
    }
}
