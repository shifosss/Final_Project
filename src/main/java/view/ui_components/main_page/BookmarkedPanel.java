package view.ui_components.main_page;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The panel where the bookmarked recipes will show.
 */
public class BookmarkedPanel extends JPanel {
    public static final int TITLE_FONT_SIZE = 24;
    public static final int TITLE_BOX_HEIGHT = 20;
    public static final int RECIPE_PANEL_COLS = 3;
    public static final int RECIPE_PANEL_HORIZONTAL_GAP = 10;
    public static final int RECIPE_PANEL_VERTICAL_GAP = 10;
    private final JPanel recipesPanel;

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
        title.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        // Add some vertical spacing
        this.add(Box.createRigidArea(new Dimension(0, TITLE_BOX_HEIGHT)));

        // Panel for recommendation recipes
        this.recipesPanel = new JPanel(new GridLayout(1, RECIPE_PANEL_COLS, RECIPE_PANEL_HORIZONTAL_GAP, RECIPE_PANEL_VERTICAL_GAP));
        recipesPanel.setBackground(Color.WHITE);

        this.add(recipesPanel);
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
