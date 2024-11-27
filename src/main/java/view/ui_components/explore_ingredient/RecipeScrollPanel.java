package view.ui_components.explore_ingredient;

import data_access.CocktailDataAccessObject;
import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeScrollPanel extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int RECOMMENDATIONS_COUNT = 3;

    private final JPanel recipePanel;
    private final JScrollPane scrollPane;
    private final ServiceManager serviceManager;
    private boolean isExploreMode = false;

    private List<Recipe> currentRecipes = new ArrayList<>();
    private int currentRecipeIndex = -1;

    public RecipeScrollPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        recipePanel = new JPanel(new GridLayout(ROW, COL, H_GAP, V_GAP));
        recipePanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(recipePanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        // Add initial empty state
        showEmptyState();
    }

    public void displayRecipes(List<Recipe> recipes) {
        this.currentRecipes = recipes; // Store the current recipes
        recipePanel.removeAll();
        recipePanel.setLayout(new GridLayout(0, COL, H_GAP, V_GAP));  // Reset to grid layout

        if (recipes == null || recipes.isEmpty()) {
            if (!isExploreMode) {
                showEmptyState();
            }
            else {
                // Show "No results found" message for explore mode
                showNoResultsMessage();
            }
        }
        else {
            final List<JPanel> recipePanels = parseToPanel(recipes);
            for (JPanel recipe : recipePanels) {
                recipePanel.add(recipe);
            }
        }

        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private void showNoResultsMessage() {
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

        JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setBackground(Color.WHITE);
        JLabel messageLabel = new JLabel("No cocktails found with this ingredient");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        messageLabel.setForeground(new Color(108, 117, 125));
        messagePanel.add(messageLabel);

        recipePanel.add(Box.createVerticalGlue());
        recipePanel.add(messagePanel);
        recipePanel.add(Box.createVerticalGlue());
    }

    public void clearRecipes() {
        if (!isExploreMode) {
            recipePanel.removeAll();
            showEmptyState();
            recipePanel.revalidate();
            recipePanel.repaint();
        }
    }

    public void setExploreMode(boolean exploreMode) {
        this.isExploreMode = exploreMode;
        if (exploreMode) {
            recipePanel.removeAll();
            recipePanel.setLayout(new GridLayout(0, COL, H_GAP, V_GAP));
        } else {
            clearRecipes();
        }
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
        recipePanel.add(searchPanel);
        recipePanel.add(Box.createVerticalGlue());
    }

    public Recipe getNextRecipe(Recipe currentRecipe) {
        if (currentRecipes == null || currentRecipes.isEmpty()) {
            return null;
        }

        // Find current recipe index
        currentRecipeIndex = -1;
        for (int i = 0; i < currentRecipes.size(); i++) {
            if (currentRecipes.get(i).getId() == currentRecipe.getId()) {
                currentRecipeIndex = i;
                break;
            }
        }

        // Return next recipe if available
        if (currentRecipeIndex >= 0 && currentRecipeIndex < currentRecipes.size() - 1) {
            return currentRecipes.get(currentRecipeIndex + 1);
        }
        return null;
    }

    private List<JPanel> parseToPanel(List<Recipe> recipes) {
        final List<JPanel> panels = new ArrayList<>();
        for (Recipe recipe : recipes) {
//            final SearchRecipePanel srp = new SearchRecipePanel(serviceManager);
//            srp.addRecipe(recipe);
//            panels.add(srp);
        }
        return panels;
    }
}