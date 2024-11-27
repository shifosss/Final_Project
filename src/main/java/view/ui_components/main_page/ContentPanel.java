package view.ui_components.main_page;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.services.ServiceManager;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that contains the contents to be shown in the main page.
 * This includes but not limited to:
 *  Recommended Recipes
 *  BookmarkedRecipes
 */
public class ContentPanel extends AbstractViewDecorator<HomePageState> {
    private final JScrollPane scrollPane;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public ContentPanel(HomePageViewModel homePageViewModel,
                        HomePageController homePageController,
                        ServiceManager serviceManager, PageView<HomePageState> pageView) {
        super(pageView);
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        setLayout(new GridLayout(0, 1, 10, 10));

        scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    @Override
    public void update(HomePageState homePageState) {
        super.getTempPage().update(homePageState);
    }

}
