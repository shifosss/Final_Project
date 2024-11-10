package view.ui_components.recipe_detail;

import javax.swing.*;

import entities.recipe.Recipe;

import java.awt.*;

/**
 * Contains the title of the recipe.
 */
public class RecipeTitlePanel extends JPanel {
    private JLabel titleLabel;

    public RecipeTitlePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        titleLabel = new JLabel("Recipe Title", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel, BorderLayout.WEST);
    }

    public void updateComponents(Recipe recipe) {
        titleLabel.setText(recipe.getName() + " (ID: " + recipe.getId() + ")");
    }
}
