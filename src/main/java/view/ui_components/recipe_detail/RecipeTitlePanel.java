package view.ui_components.recipe_detail;

import javax.swing.*;

import entities.recipe.Recipe;

/**
 * Contains the title of the recipe.
 */
public class RecipeTitlePanel extends JPanel {
    private final Recipe recipe;

    public RecipeTitlePanel(Recipe recipe) {
        this.recipe = recipe;
    }
}
