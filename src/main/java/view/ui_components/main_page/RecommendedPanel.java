package view.ui_components.main_page;

import entities.recipe.Recipe;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel that contains all the recommended recipes to the user.
 */
public class RecommendedPanel extends JPanel {
    private static final int STEP = 1;
    private static final int SHOWN_RECIPE = 3;

    private int currentIndex;
    private List<Recipe> recommendedRecipes = new ArrayList<>();

    private final JButton prevButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");
    private final JPanel recommendedRecipesPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public RecommendedPanel(HomePageViewModel homePageViewModel,
                            HomePageController homePageController,
                            ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        // Recommendations section
        setLayout(new GridLayout(1, 3));
        this.setBackground(Color.WHITE);

        // Panel for recommendation recipes
        this.recommendedRecipesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        recommendedRecipesPanel.setBackground(Color.WHITE);

        prevButton.addActionListener(event -> {
            currentIndex -= STEP;
            updatePanel(recommendedRecipes);
        });

        nextButton.addActionListener(event -> {
            currentIndex += STEP;
            updatePanel(recommendedRecipes);
        });

        this.add(new JPanel().add(prevButton), BorderLayout.WEST);
        this.add(recommendedRecipesPanel, BorderLayout.CENTER);
        this.add(new JPanel().add(nextButton), BorderLayout.EAST);
    }

    /**
     * Updates the panel fields.
     * @param randomRecipes the randomRecipes to be recommended.
     */
    public void updatePanel(List<Recipe> randomRecipes) {
        recommendedRecipes = randomRecipes;

        recommendedRecipesPanel.removeAll();
        // Add random recipes
        for (int i = currentIndex; i < currentIndex + SHOWN_RECIPE && i < randomRecipes.size(); i++) {
            final HomeRecipeThumbnailPanel randomRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel,
                    homePageController,
                    serviceManager);

            recommendedRecipesPanel.add(randomRecipeThumbnailPanel);
            randomRecipeThumbnailPanel.addRecipe(randomRecipes.get(i));
        }

        // Revalidate and repaint to update the UI
        recommendedRecipesPanel.revalidate();
        recommendedRecipesPanel.repaint();

        prevButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex + STEP < randomRecipes.size());
    }
}
