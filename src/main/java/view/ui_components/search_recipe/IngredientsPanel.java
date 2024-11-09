package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import entities.recipe.Ingredient;
import data_access.CocktailDataAccessObject;
import interface_adapter.services.ServiceManager;

public class IngredientsPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final int GRID_COLUMNS = 3;
    private final CocktailDataAccessObject dataAccess;
    private final RecipeScrollPanel recipeScrollPanel;
    private final ServiceManager serviceManager;

    public IngredientsPanel(ServiceManager serviceManager, RecipeScrollPanel recipeScrollPanel) {
        this.serviceManager = serviceManager;
        this.recipeScrollPanel = recipeScrollPanel;
        this.dataAccess = new CocktailDataAccessObject();

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        // Create header
        JLabel headerLabel = new JLabel("Available Ingredients");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Create grid panel for ingredients
        JPanel gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, 10, 10));
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load and display ingredients
        List<Ingredient> ingredients = dataAccess.getIngredientsList();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.getName().isEmpty()) {
                JButton ingredientButton = createIngredientButton(ingredient);
                gridPanel.add(ingredientButton);
            }
        }

        // Add components to scrollable panel
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Add everything to main panel
        add(headerLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createIngredientButton(Ingredient ingredient) {
        JButton button = new JButton(ingredient.getName());
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        button.addActionListener(e -> {
            // Search recipes with this ingredient
            recipeScrollPanel.displayRecipes(
                    dataAccess.exploreRecipeByIngredients(ingredient.getName())
            );
        });

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(245, 245, 245));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255));
            }
        });

        return button;
    }
}