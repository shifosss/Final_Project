package view.ui_components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import domain.entities.recipe.Recipe;

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

    public RecipeScrollPanel() {
        // Set layout for main panel
        setLayout(new BorderLayout());

        // Initialize the grid panel for recipes with a grid layout (e.g., 3 columns)
        recipePanel = new JPanel(new GridLayout(ROW, COL, H_GAP, V_GAP));

        // Wrap recipe panel in a scroll pane
        scrollPane = new JScrollPane(recipePanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);

        // Set panel properties as needed
        scrollPane.setPreferredSize(new Dimension(600, 400));
    }

    /**
     * Updates the recipe scroll panel given the new recipes.
     * @param recipes a list of Recipe.
     */
    public void displayRecipes(List<Recipe> recipes) {
        final List<JPanel> recipePanels = parseToPanel(recipes);
        recipePanel.removeAll();

        // Add each recipe panel to the grid panel
        for (JPanel recipe : recipePanels) {
            recipePanel.add(recipe);
        }

        // Refresh the panel to show updated content
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    private List<JPanel> parseToPanel(List<Recipe> recipes) {
        final List<JPanel> panels = new ArrayList<>();
        for (Recipe recipe : recipes) {
            panels.add(new SearchRecipePanel(
                    recipe
            ));
        }
        return panels;
    }
}
