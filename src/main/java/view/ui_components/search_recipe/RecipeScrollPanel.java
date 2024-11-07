package view.ui_components.search_recipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import data_access.CocktailDataAccessObject;

/**
 * Recipe Scroll Panel that shows the recipe in a scrollable grid panel.
 */
public class RecipeScrollPanel extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int RECOMMENDATIONS_COUNT = 3;

    private final JPanel recipePanel;
    private final JScrollPane scrollPane;
    private final ServiceManager serviceManager;
    private final CocktailDataAccessObject cocktailDataAccessObject;

    public RecipeScrollPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.cocktailDataAccessObject = new CocktailDataAccessObject();
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

    public void clearRecipes() {
        recipePanel.removeAll();
        showEmptyState();
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showEmptyState() {
        // Change layout to BoxLayout for vertical arrangement
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

        // Welcome message panel
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome to Recipe Search!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(108, 117, 125));
        welcomePanel.add(welcomeLabel);

        // Recommendations section
        JPanel recommendationsPanel = new JPanel();
        recommendationsPanel.setLayout(new BoxLayout(recommendationsPanel, BoxLayout.Y_AXIS));
        recommendationsPanel.setBackground(Color.WHITE);

        // Recommendations title
        JLabel recommendationsTitle = new JLabel("Daily Recommendations");
        recommendationsTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        recommendationsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recommendationsPanel.add(recommendationsTitle);

        // Add some vertical spacing
        recommendationsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel for recommendation recipes
        JPanel recommendedRecipesPanel = new JPanel(new GridLayout(1, RECOMMENDATIONS_COUNT, H_GAP, V_GAP));
        recommendedRecipesPanel.setBackground(Color.WHITE);

        // Add random recipes
        for (int i = 0; i < RECOMMENDATIONS_COUNT; i++) {
            Recipe randomRecipe = cocktailDataAccessObject.searchRandomRecipe();
            SearchRecipePanel randomRecipePanel = new SearchRecipePanel(serviceManager);
            randomRecipePanel.addRecipe(randomRecipe);
            recommendedRecipesPanel.add(randomRecipePanel);
        }
        recommendationsPanel.add(recommendedRecipesPanel);

        // Search message panel
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        JLabel searchLabel = new JLabel("Search for recipes!");
        searchLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        searchLabel.setForeground(new Color(108, 117, 125));
        searchPanel.add(searchLabel);

        // Add all components with glue for spacing
        recipePanel.add(Box.createVerticalGlue());
        recipePanel.add(welcomePanel);
        recipePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        recipePanel.add(recommendationsPanel);
        recipePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        recipePanel.add(searchPanel);
        recipePanel.add(Box.createVerticalGlue());
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