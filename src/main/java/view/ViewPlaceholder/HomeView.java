package view.ViewPlaceholder;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.ui_components.random_recipe.RandomRecipeThumbnailPanel;
import view.ui_components.main_page.HeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final String viewName = "home page";

    private final JTextField searchTextField = new JTextField(15);
    private JPanel recommendationsPanel;
    private JPanel recommendedRecipesPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;
    private final RecipeDetailController recipeDetailController;


    public HomeView(HomePageViewModel homePageViewModel,
                    HomePageController homePageController,
                    RecipeDetailController recipeDetailController,
                    ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.recipeDetailController = recipeDetailController;
        this.serviceManager = serviceManager;

        // Create the "Explore by Ingredients" button
        JButton exploreIngredientsButton = createExploreIngredientsButton();

        // Create the HeaderPanel with the button
        HeaderPanel headerPanel = new HeaderPanel("Recipe Lookup", exploreIngredientsButton);

        // Create the search bar panel
        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.add(searchTextField, BorderLayout.CENTER);
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                homePageController.switchToSearchRecipeView();
            }
        });

        // Combine the HeaderPanel and search bar into a single panel
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(headerPanel, BorderLayout.NORTH);
        northPanel.add(searchBarPanel, BorderLayout.CENTER);

        // Set up layout and add components
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);

        // Recommendations section
        this.recommendationsPanel = new JPanel();
        recommendationsPanel.setLayout(new BoxLayout(recommendationsPanel, BoxLayout.Y_AXIS));
        recommendationsPanel.setBackground(Color.WHITE);

        JLabel recommendationsTitle = new JLabel("Recommendations");
        recommendationsTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        recommendationsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        recommendationsPanel.add(recommendationsTitle);

        recommendationsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        this.recommendedRecipesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        recommendedRecipesPanel.setBackground(Color.WHITE);

        add(recommendationsPanel, BorderLayout.CENTER);
        homePageViewModel.addPropertyChangeListener(this);
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
        searchTextField.setText(query);
        updateRandomRecipePanel(randomRecipes);
    }

    private void updateRandomRecipePanel(List<Recipe> randomRecipes) {
        recommendedRecipesPanel.removeAll();
        // Add random recipes
        for (int i = 0; i < 3; i++) {
            Recipe randomRecipe = randomRecipes.get(i);
            RandomRecipeThumbnailPanel randomRecipeThumbnailPanel = new RandomRecipeThumbnailPanel(
                    homePageViewModel,
                    homePageController,
                    recipeDetailController,
                    serviceManager);
            recommendedRecipesPanel.add(randomRecipeThumbnailPanel);
            randomRecipeThumbnailPanel.addRecipe(randomRecipe);
        }
        recommendationsPanel.add(recommendedRecipesPanel);
    }

    /**
     * Creates the "Explore by Ingredients" button.
     */
    public JButton createExploreIngredientsButton() {
        JButton exploreIngredientsButton = new JButton("Explore by Ingredients");
        exploreIngredientsButton.addActionListener(e -> {
            // Call the controller to switch views
            homePageController.switchToExploreIngredientView();
        });
        return exploreIngredientsButton;
    }
}
