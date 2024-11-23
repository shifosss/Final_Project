package view.ui_components.meal_recipe_detail;

import entities.recipe.MealRecipe;

import javax.swing.*;
import java.awt.*;

public class RecipeTitlePanel extends JPanel {
    private JLabel titleLabel;

    public RecipeTitlePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        titleLabel = new JLabel("Recipe Title", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel, BorderLayout.WEST);
    }

    public void updateComponents(MealRecipe mealrecipe) {
        titleLabel.setText(mealrecipe.getName() + " (ID: " + mealrecipe.getId() + ")");
    }
}
