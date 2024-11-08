package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that contains a back button, search field, and search button.
 */
public class SearchPanel extends JPanel {
    private final JButton backButton;
    private final JTextField searchField;
    private final JButton searchButton;
    private final RecipeScrollPanel recipeScrollPanel;

    public SearchPanel(JButton backButton, JTextField searchField,
                       JButton searchButton, RecipeScrollPanel recipeScrollPanel) {
        this.backButton = backButton;
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.recipeScrollPanel = recipeScrollPanel;

        setLayout(new BorderLayout());

        // Add components
        add(backButton, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);

        // Configure back button
        setupBackButton();
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