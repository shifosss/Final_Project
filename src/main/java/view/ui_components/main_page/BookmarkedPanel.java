package view.ui_components.main_page;

import entities.recipe.Recipe;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.services.ServiceManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The panel where the bookmarked recipes will show.
 */
public class BookmarkedPanel extends JPanel {
    private final JPanel recipesPanel;
    private final JScrollPane scrollPane;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public BookmarkedPanel(HomePageViewModel homePageViewModel,
                           HomePageController homePageController,
                           ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        // Recommendations section
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        // Recommendations title
        final JLabel title = new JLabel("Bookmarked Recipes");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        // Add some vertical spacing
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel for recommendation recipes
        this.recipesPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        recipesPanel.setBackground(Color.WHITE);

        this.add(recipesPanel);

        scrollPane = new JScrollPane(recipesPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane);
    }

    /**
     * Updates the panel.
     * @param bookmarkedRecipes the bookmarked recipes to be shown.
     */
    public void updatePanel(List<Recipe> bookmarkedRecipes) {
        recipesPanel.removeAll();
        // Add random recipes
        for (Recipe recipe : bookmarkedRecipes) {
            final HomeRecipeThumbnailPanel randomRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel,
                    homePageController,
                    serviceManager);
            recipesPanel.add(randomRecipeThumbnailPanel);
            randomRecipeThumbnailPanel.addRecipe(recipe);
        }
    }
}
