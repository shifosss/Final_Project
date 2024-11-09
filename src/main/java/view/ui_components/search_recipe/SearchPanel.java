package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that contains a centered back button, search field, and search button.
 */
public class SearchPanel extends JPanel {
    private final JButton backButton;
    private final JTextField searchField;
    private final RecipeScrollPanel recipeScrollPanel;

    public SearchPanel(JButton backButton, JTextField searchField, JButton searchButton, RecipeScrollPanel recipeScrollPanel) {
        this.backButton = backButton;
        this.searchField = searchField;
        this.recipeScrollPanel = recipeScrollPanel;

        // Use BorderLayout for the main panel to enable center alignment
        setLayout(new BorderLayout());

        // Create a panel for all components with center alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        // Set preferred size for search field
        searchField.setPreferredSize(new Dimension(300, 30));

        // Add all components to the center panel
        centerPanel.add(backButton);
        centerPanel.add(searchField);
        centerPanel.add(searchButton);

        // Add the center panel to the main panel's center position
        add(centerPanel, BorderLayout.CENTER);

        // Configure back button
        setupBackButton();

        // Optional: Add some padding at the top
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
    }

    private void setupBackButton() {
        backButton.addActionListener(e -> {
            // Clear the search field
            searchField.setText("");

            // Clear the recipe display
            recipeScrollPanel.clearRecipes();
        });
    }
}