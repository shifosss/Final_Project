package view.ui_components.user_profile;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileViewModel;
import view.ui_components.main_page.HomeRecipeThumbnailPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomRecipePanel extends JPanel {

    private final JScrollPane scrollPane;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

    public CustomRecipePanel(UserProfileViewModel userProfileViewModel,
                             UserProfileController userProfileController,
                             ServiceManager serviceManager) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileController = userProfileController;
        this.serviceManager = serviceManager;

        setBackground(new Color(211, 211, 211));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(169, 169, 169), 2),
                "Custom Recipe",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                Color.DARK_GRAY
        ));

        scrollPane = new JScrollPane(this,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Updates the custom recipe panel which consists of a list of recipes the user has created.
     * @param createdRecipes the list of recipes created by user.
     */
    public void updateComponents(List<Recipe> createdRecipes) {
        removeAll();

        for (Recipe recipe : createdRecipes) {
            final UserProfileRecipeThumbnailPanel customRecipePanel = new UserProfileRecipeThumbnailPanel(
                    userProfileViewModel,
                    userProfileController,
                    serviceManager
            );

            customRecipePanel.addRecipe(recipe);
            add(customRecipePanel);
        }
    }
}
