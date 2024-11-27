package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import interface_adapter.preference.PreferenceController;
import interface_adapter.preference.PreferenceViewModel;
import view.ui_components.preference_view.ButtonPanel;
import view.ui_components.preference_view.IngredientPanel;
import view.ui_components.preference_view.TitlePanel;


/**
 * The PreferenceView class represents the UI for managing user dietary preferences.
 */
/**
 * The PreferenceView class represents the UI for managing user dietary preferences.
 */
public class PreferenceView extends JPanel {
    private final PreferenceController controller;
    private final IngredientPanel ingredientPanel;

    public PreferenceView(PreferenceController controller, PreferenceViewModel viewModel, List<String> ingredients) {
        this.controller = controller;

        // Set layout and background
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40)); // Dark gray background

        // Add Title Panel
        TitlePanel titlePanel = new TitlePanel("Ingredients to Avoid", new Color(
                40, 40, 40), new Color(255, 165, 0));
        add(titlePanel, BorderLayout.NORTH);

        // Add Ingredient Panel
        this.ingredientPanel = new IngredientPanel(
                ingredients,
                new Color(40, 40, 40), // Background color
                new Color(255, 165, 0), // Border color
                new Color(255, 87, 34), // Selected color
                new Color(60, 60, 60) // Unselected color
        );
        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        add(scrollPane, BorderLayout.CENTER);

        // Add Button Panel
        ButtonPanel buttonPanel = new ButtonPanel(
                e -> savePreferences(),
                e -> controller.navigateToHome(),
                new Color(255, 87, 34), // Save button color
                new Color(100, 100, 100) // Back button color
        );
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Gathers selected ingredients and updates the preferences controller.
     */
    private void savePreferences() {
        List<String> selectedIngredients = ingredientPanel.getSelectedIngredients();
        controller.updatePreferences(selectedIngredients);
        JOptionPane.showMessageDialog(this, "Preferences saved successfully!");
    }
}