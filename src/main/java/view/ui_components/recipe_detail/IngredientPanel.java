package view.ui_components.recipe_detail;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Contains the ingredients of a recipe.
 */
public class IngredientPanel extends JPanel {
    final JPanel ingredientsListPanel;

    public IngredientPanel() {
        ingredientsListPanel = new JPanel();
        ingredientsListPanel.setLayout(new BoxLayout(ingredientsListPanel, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        final JLabel headerLabel = new JLabel("Ingredients:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);
    }

    /**
     * Updates components.
     * @param recipe the recipe entity.
     */
    public void updateComponents(Recipe recipe) {
        ingredientsListPanel.removeAll();

        final List<Ingredient> ingredients = recipe.getIngredients();

        if (ingredients == null || ingredients.isEmpty()) {
            ingredientsListPanel.add(new JLabel("No ingredients available"));
        }
        else {
            for (Ingredient ingredient : ingredients) {
                if (ingredient != null && ingredient.getName() != null && !ingredient.getName().isEmpty()) {
                    final JCheckBox ingredientCheckBox = new JCheckBox(
                            ingredient.getName() + " - " + ingredient.getMeasure());
                    ingredientCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
                    ingredientsListPanel.add(ingredientCheckBox);
                }
            }
        }
        add(ingredientsListPanel, BorderLayout.CENTER);
    }
}
