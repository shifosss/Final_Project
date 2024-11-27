package view.ui_components.main_page;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel that contains all the recommended recipes to the user.
 */
public class RecommendedPanel extends AbstractViewDecorator<HomePageState> {
    private List<Recipe> recommendedRecipes = new ArrayList<>();

    private final JPanel gridPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public RecommendedPanel(HomePageViewModel homePageViewModel,
                            HomePageController homePageController,
                            ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JLabel headerLabel = new JLabel("Recommended Recipes", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        final JScrollPane scrollPane = new JScrollPane(gridPanel);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(HomePageState state) {
        super.getTempPage().update(state);
        gridPanel.removeAll();
        final List<Recipe> recommendedRecipes = state.getRandomRecipe();

        for (Recipe recipe : recommendedRecipes) {
            final HomeRecipeThumbnailPanel homeRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel, homePageController, serviceManager);
            homeRecipeThumbnailPanel.addRecipe(recipe);
            gridPanel.add(homeRecipeThumbnailPanel);
        }
    }
}
