package view.ui_components.recipe_detail;

import entities.recipe.Recipe;

import javax.swing.*;

/**
 * Contains the ingredients of a recipe.
 */
public class IngredientPanel extends JPanel {
    public IngredientPanel(Recipe recipe) {
        add(new JLabel("Ingredient: "));
    }
}
