package view.ui_components.custom_recipe;

import interface_adapter.custom_recipe.CustomIngredientsController;
import interface_adapter.custom_recipe.CustomIngredientsViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class CustomIngredientsPanel extends JPanel {
    private final CustomIngredientsController controller;
    private final CustomIngredientsViewModel viewModel;

    public CustomIngredientsPanel(CustomIngredientsController controller, CustomIngredientsViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 165, 0));
        setBorder(createRoundedBorder("Ingredients and Measurements", new Color(255, 87, 34)));

        // Initialize the interface
        refreshIngredients();
    }

    /**
     * refresh ingredients
     */
    public void refreshIngredients() {
        removeAll(); // clean current content
        List<String[]> ingredients = viewModel.getIngredients(); // Get ingredients from ViewModel.
        for (int i = 0; i < ingredients.size(); i++) {
            String[] ingredient = ingredients.get(i);
            add(createRow(ingredient[0], ingredient[1], i)); // Add on each row
        }
        revalidate(); // Revalidate
        repaint(); // Repaint
    }

    /**
     * Create an ingredient row
     */
    private JPanel createRow(String ingredient, String measurement, int index) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBackground(new Color(255, 140, 0));

        // Ingredient name and measurement input fields
        JTextField ingredientField = new JTextField(ingredient, 10);
        JTextField measurementField = new JTextField(measurement, 10);

        // Add button
        JButton addButton = new JButton("+");
        addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            controller.addIngredient(); // Call the controller to add an ingredient.
            refreshIngredients(); // Refresh
        });

        // Remove button
        JButton removeButton = new JButton("-");
        removeButton.setBackground(new Color(255, 69, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(e -> {
            controller.removeIngredient(index); // Call the controller to delete an ingredient.
            refreshIngredients(); // Refresh
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
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
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