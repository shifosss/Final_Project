package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that contains a centered back button, search field, search button, and explore button
 * with a grey background and comfortable padding.
 */
public class SearchPanel extends JPanel {
    private final JButton backButton;
    private final JTextField searchField;
    private final JButton searchButton;
    private final JButton exploreButton;
    private final RecipeScrollPanel recipeScrollPanel;
    private static final int PANEL_HEIGHT = 50;
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    public SearchPanel(JButton backButton, JTextField searchField, JButton searchButton,
                       JButton exploreButton, RecipeScrollPanel recipeScrollPanel) {
        this.backButton = backButton;
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.exploreButton = exploreButton;
        this.recipeScrollPanel = recipeScrollPanel;

        // Set a fixed size for the panel
        setPreferredSize(new Dimension(getPreferredSize().width, PANEL_HEIGHT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));

        // Use BoxLayout to prevent vertical stretching
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a panel for all components with center alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        // Set component sizes
        int componentHeight = 25;
        searchField.setPreferredSize(new Dimension(600, componentHeight));
        backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width, componentHeight));
        searchButton.setPreferredSize(new Dimension(100, componentHeight)); // Fixed width for consistency
        exploreButton.setPreferredSize(new Dimension(150, componentHeight)); // Wider for "Explore by Ingredients" text

        // Style the search field
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

        // Style the buttons
        styleButton(searchButton);
        styleButton(exploreButton);
        styleButton(backButton);

        // Add all components to the center panel
        centerPanel.add(backButton);
        centerPanel.add(searchField);
        centerPanel.add(searchButton);
        centerPanel.add(exploreButton);

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

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(245, 245, 245));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
    }

    public void setSearchEnabled(boolean enabled) {
        searchField.setEnabled(enabled);
        searchButton.setEnabled(enabled);
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