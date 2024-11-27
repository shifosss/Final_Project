package view.ui_components.custom_recipe;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom ingredients panel that contains the users input (ingredient and measurements)
 */
public class CustomIngredientsPanel extends JPanel {
    private final CustomRecipeController controller;
    private final CustomRecipeViewModel viewModel;

    public CustomIngredientsPanel(CustomRecipeController controller, CustomRecipeViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 165, 0));
        setBorder(createRoundedBorder("Ingredients and Measurements", new Color(255, 87, 34)));
        // Initialize the interface
        refreshIngredients();
    }

    /**
     * Refresh ingredients.
     */
    public void refreshIngredients() {
        removeAll();
        final List<String[]> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            final String[] ingredient = ingredients.get(i);
            add(createRow(ingredient[0], ingredient[1], i));
        }
        revalidate();
        repaint();
    }

    /**
     * Create an ingredient row
     */
    private JPanel createRow(String ingredient, String measurement, int index) {
        final JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBackground(new Color(255, 140, 0));

        // Ingredient name and measurement input fields
        final JTextField ingredientField = new JTextField(ingredient, 10);
        final JTextField measurementField = new JTextField(measurement, 10);

        // Add button
        final JButton addButton = new JButton("+");
        addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(event -> {
        });

        // Remove button
        final JButton removeButton = new JButton("-");
        removeButton.setBackground(new Color(255, 69, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(event -> {
            refreshIngredients();
        });

        // Add components to the row panel
        rowPanel.add(new JLabel("Ingredient:"));
        rowPanel.add(ingredientField);
        rowPanel.add(new JLabel("Measurement:"));
        rowPanel.add(measurementField);
        rowPanel.add(addButton);
        rowPanel.add(removeButton);

        return rowPanel;
    }

    private CompoundBorder createRoundedBorder(String title, Color color) {
        final TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Color.WHITE
        );
        return BorderFactory.createCompoundBorder(
                titledBorder,
                new EmptyBorder(10, 10, 10, 10)
        );
    }
}