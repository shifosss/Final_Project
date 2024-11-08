package view.ui_components.recipe_detail;

import entities.recipe.Recipe;

import javax.swing.*;

/**
 * Contains instruction about the recipe.
 */
public class InstructionPanel extends JPanel {
    public InstructionPanel(Recipe recipe) {
        add(new JLabel("Instruction:"));
    }
}
