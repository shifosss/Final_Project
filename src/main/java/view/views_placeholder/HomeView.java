package view.views_placeholder;

import entities.recipe.Recipe;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.ui_components.random_recipe.RandomRecipeThumbnailPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final String viewName = "home page";

    private final JButton searchButton = new JButton("Search");
    private final JButton exploreByIngredientButton = new JButton("Explore By Ingredient");
    private JPanel recommendationsPanel;
    private JPanel recommendedRecipesPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final SearchRecipeController searchRecipeController;
    private final RecipeDetailController recipeDetailController;
    private final ExploreIngredientController exploreIngredientController;

    public HomeView(HomePageViewModel homePageViewModel,
                    SearchRecipeController searchRecipeController,
                    RecipeDetailController recipeDetailController,
                    ExploreIngredientController exploreIngredientController,
                    ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.searchRecipeController = searchRecipeController;
        this.recipeDetailController = recipeDetailController;
        this.exploreIngredientController = exploreIngredientController;
        this.serviceManager = serviceManager;

        // Recommendations section
        this.recommendationsPanel = new JPanel();
        recommendationsPanel.setLayout(new BoxLayout(recommendationsPanel, BoxLayout.Y_AXIS));
        recommendationsPanel.setBackground(Color.WHITE);

        // Recommendations title
        final JLabel recommendationsTitle = new JLabel("Recommendations");
        recommendationsTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        recommendationsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recommendationsPanel.add(recommendationsTitle);

        // Add some vertical spacing
        recommendationsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel for recommendation recipes
        this.recommendedRecipesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        recommendedRecipesPanel.setBackground(Color.WHITE);

        final JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        searchBar.add(searchButton);
        searchBar.add(exploreByIngredientButton);
        searchButton.addActionListener(event -> recipeDetailController.switchToSearchView());

        exploreByIngredientButton.addActionListener(event -> exploreIngredientController.switchToExploreIngredients());

        homePageViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());

        add(searchBar, BorderLayout.NORTH);
        add(recommendationsPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final HomePageState state = (HomePageState) event.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    private void setFields(HomePageState state) {
        final String query = state.getQuery();
        final List<Recipe> randomRecipes = state.getRandomRecipe();
        searchButton.setText(query);
        updateRandomRecipePanel(randomRecipes);
    }

    private void updateRandomRecipePanel(List<Recipe> randomRecipes) {
        recommendedRecipesPanel.removeAll();
        // Add random recipes
        for (final Recipe randomRecipe : randomRecipes) {
            final RandomRecipeThumbnailPanel randomRecipeThumbnailPanel = new RandomRecipeThumbnailPanel(
                    homePageViewModel,
                    searchRecipeController,
                    recipeDetailController,
                    serviceManager);
            recommendedRecipesPanel.add(randomRecipeThumbnailPanel);
            randomRecipeThumbnailPanel.addRecipe(randomRecipe);
        }
        recommendationsPanel.add(recommendedRecipesPanel);
    }
}
