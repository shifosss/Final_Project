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
import java.util.List;

/**
 * The panel where the bookmarked recipes will show.
 */
public class BookmarkedDecoratorPanel extends AbstractViewDecorator<HomePageState> {
    private final JPanel gridPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public BookmarkedDecoratorPanel(HomePageViewModel homePageViewModel,
                                    HomePageController homePageController,
                                    ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        // Recommendations section
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        setLayout(new BorderLayout(10, 10));
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
        final List<Recipe> bookmarkedRecipes = state.getBookmarkedRecipes();
        gridPanel.removeAll();
        for (Recipe recipe : bookmarkedRecipes) {
            final HomeRecipeThumbnailPanel homeRecipeThumbnailPanel = new HomeRecipeThumbnailPanel(
                    homePageViewModel, homePageController, serviceManager);
            homeRecipeThumbnailPanel.addRecipe(recipe);
            gridPanel.add(homeRecipeThumbnailPanel);
        }
    }
}
