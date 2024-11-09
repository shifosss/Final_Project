package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that contains a centered back button, search field, and search button
 * with a grey background and comfortable padding.
 */
public class SearchPanel extends JPanel {
    private final JButton backButton;
    private final JTextField searchField;
    private final RecipeScrollPanel recipeScrollPanel;
    private static final int PANEL_HEIGHT = 50; // Increased panel height
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245); // Light grey background

    public SearchPanel(JButton backButton, JTextField searchField, JButton searchButton, RecipeScrollPanel recipeScrollPanel) {
        this.backButton = backButton;
        this.searchField = searchField;
        this.recipeScrollPanel = recipeScrollPanel;

        // Set a fixed size for the panel
        setPreferredSize(new Dimension(getPreferredSize().width, PANEL_HEIGHT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));

        // Use BoxLayout to prevent vertical stretching
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a panel for all components with center alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0)); // Increased horizontal gap

        // Set component sizes
        int componentHeight = 25; // Slightly larger components
        searchField.setPreferredSize(new Dimension(800, componentHeight)); // Wider search field
        backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width, componentHeight));
        searchButton.setPreferredSize(new Dimension(searchButton.getPreferredSize().width, componentHeight));

        // Optional: Add rounded borders to components for better aesthetics
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

        // Add all components to the center panel
        centerPanel.add(backButton);
        centerPanel.add(searchField);
        centerPanel.add(searchButton);

        // Center the components vertically in the fixed height panel
        centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add vertical glue to center the components
        add(Box.createVerticalGlue());
        add(centerPanel);
        add(Box.createVerticalGlue());

        // Configure back button
        setupBackButton();

        // Set the background colors
        setBackground(BACKGROUND_COLOR);
        centerPanel.setBackground(BACKGROUND_COLOR);

        // Add a subtle bottom border for separation
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension prefSize = super.getPreferredSize();
        return new Dimension(prefSize.width, PANEL_HEIGHT);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension maxSize = super.getMaximumSize();
        return new Dimension(maxSize.width, PANEL_HEIGHT);
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