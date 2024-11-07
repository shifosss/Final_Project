package view.ui_components.search_recipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;

/**
 * Recipe Scroll Panel that shows the recipe in a scrollable grid panel.
 */
public class RecipeScrollPanel extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    private final JPanel recipePanel;
    private final JScrollPane scrollPane;
    private final ServiceManager serviceManager;

    public RecipeScrollPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        // Set layout for main panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Initialize the grid panel for recipes with a grid layout
        recipePanel = new JPanel(new GridLayout(ROW, COL, H_GAP, V_GAP));
        recipePanel.setBackground(Color.WHITE);

        // Wrap recipe panel in a scroll pane
        scrollPane = new JScrollPane(recipePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set scroll pane background
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // Remove border

        // Add scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);

        // Set panel properties
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add initial empty state
        showEmptyState();
    }

    /**
     * Updates the recipe scroll panel with the new recipes.
     * @param recipes a list of Recipe.
     */
    public void displayRecipes(List<Recipe> recipes) {
        recipePanel.removeAll();
        recipePanel.setLayout(new GridLayout(ROW, COL, H_GAP, V_GAP));  // Reset to grid layout

        if (recipes == null || recipes.isEmpty()) {
            showEmptyState();
        } else {
            final List<JPanel> recipePanels = parseToPanel(recipes);
            // Add each recipe panel to the grid panel
            for (JPanel recipe : recipePanels) {
                recipePanel.add(recipe);
            }
        }

        // Refresh the panel
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    /**
     * Clears all recipes and returns to empty state
     */
    public void clearRecipes() {
        recipePanel.removeAll();
        showEmptyState();
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showEmptyState() {
        // Change layout to BorderLayout for center alignment
        recipePanel.setLayout(new BorderLayout());

        // Create a panel for centered content
        JPanel emptyStatePanel = new JPanel(new GridBagLayout());  // Use GridBagLayout for perfect centering
        emptyStatePanel.setBackground(Color.WHITE);

        // Create and configure the message label
        JLabel messageLabel = new JLabel("Search for recipes!");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24));  // Increased font size
        messageLabel.setForeground(new Color(108, 117, 125));

        // Add label to the empty state panel
        emptyStatePanel.add(messageLabel);

        // Add empty state panel to recipe panel
        recipePanel.add(emptyStatePanel, BorderLayout.CENTER);
    }

    private List<JPanel> parseToPanel(List<Recipe> recipes) {
        final List<JPanel> panels = new ArrayList<>();
        for (Recipe recipe : recipes) {
            final SearchRecipePanel srp = new SearchRecipePanel(serviceManager);
            srp.addRecipe(recipe);
            panels.add(srp);
        }
        return panels;
    }
}