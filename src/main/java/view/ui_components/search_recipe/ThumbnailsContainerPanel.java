package view.ui_components.search_recipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;

/**
 * Recipe Scroll Panel that shows the recipe in a scrollable grid panel.
 */
public class ThumbnailsContainerPanel extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;

    private final JPanel recipePanel;
    private final SearchRecipeController searchRecipeController;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final RecipeDetailController recipeDetailController;
    private final ServiceManager serviceManager;

    public ThumbnailsContainerPanel(SearchRecipeViewModel searchRecipeViewModel,
                                    SearchRecipeController searchRecipeController,
                                    RecipeDetailController recipeDetailController,
                                    ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.searchRecipeController = searchRecipeController;
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.recipeDetailController = recipeDetailController;
        // Set layout for main panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Initialize the grid panel for recipes with a grid layout
        recipePanel = new JPanel(new GridLayout(ROW, COL, H_GAP, V_GAP));
        recipePanel.setBackground(Color.WHITE);

        // Wrap recipe panel in a scroll pane
        final JScrollPane scrollPane = new JScrollPane(recipePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set scroll pane background
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);

        // Set panel properties
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add initial empty state
        clearRecipes();
    }

    /**
     * Updates the recipe scroll panel with the new recipes.
     * @param recipes a list of Recipe.
     */
    public void displayRecipes(List<Recipe> recipes) {
        recipePanel.removeAll();
        // Reset to grid layout
        recipePanel.setLayout(new GridLayout(ROW, COL, H_GAP, V_GAP));

        if (recipes == null || recipes.isEmpty()) {
            clearRecipes();
        }
        else {
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

    private void clearRecipes() {
        recipePanel.removeAll();
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private List<JPanel> parseToPanel(List<Recipe> recipes) {
        final List<JPanel> panels = new ArrayList<>();
        for (Recipe recipe : recipes) {
            final SearchThumbnailPanel srp = new SearchThumbnailPanel(
                    searchRecipeViewModel, searchRecipeController, recipeDetailController,
                    serviceManager);
            srp.addRecipe(recipe);
            panels.add(srp);
        }
        return panels;
    }
}