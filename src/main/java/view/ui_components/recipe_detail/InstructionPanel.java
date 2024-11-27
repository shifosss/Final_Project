package view.ui_components.recipe_detail;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Contains instruction about the recipe.
 */
public class InstructionPanel extends AbstractViewDecorator<RecipeDetailState> {
    private final JTextArea instructionsArea;

    public InstructionPanel(PageView<RecipeDetailState> view) {
        super(view);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        instructionsArea = new JTextArea();
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setEditable(false);
        final JLabel headerLabel = new JLabel("Instructions");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(headerLabel, BorderLayout.NORTH);
        add(instructionsArea, BorderLayout.CENTER);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);

        final Recipe recipe = state.getRecipe();
        final String instructionsText = recipe.getInstruction();
        if (instructionsText == null || instructionsText.isEmpty()) {
            instructionsArea.setText("No instructions available");
        }
        else {
            // Split the instructions by line breaks or periods, assuming each is a separate step
            final List<String> steps = Arrays.asList(instructionsText.split("\\r?\\n|\\.\\s*"));

            // Build a numbered list of instructions
            final StringBuilder numberedInstructions = new StringBuilder();
            for (int i = 0; i < steps.size(); i++) {
                final String step = steps.get(i).trim();
                if (!step.isEmpty()) {
                    numberedInstructions.append("Step ").append(i + 1).append(": ").append(step).append("\n\n");
                }
            }

            // Set the formatted, numbered instructions text
            instructionsArea.setText(numberedInstructions.toString());
        }
    }
}