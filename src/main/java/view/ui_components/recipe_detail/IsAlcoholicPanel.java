package view.ui_components.recipe_detail;

import entities.recipe.Recipe;

import javax.swing.*;

/**
 * The panel showing if a recipe is alcoholic.
 */
public class IsAlcoholicPanel extends JPanel {
    private final JLabel messageLabel;

    public IsAlcoholicPanel() {
        messageLabel = new JLabel();
        add(messageLabel);
    }

    /**
     * updates the panel components.
     * @param recipe the recipe data.
     */
    public void updateComponents(Recipe recipe) {
        messageLabel.setText(recipe.getIsAlcoholic());
    }
}
