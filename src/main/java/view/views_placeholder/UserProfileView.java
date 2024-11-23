package view.views_placeholder;

import entities.recipe.Recipe;
import entities.user.User;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import view.PageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class UserProfileView extends JPanel implements PageView, ActionListener, PropertyChangeListener {

    private final JLabel usernameLabel;
    private final JButton goHomeButton = new JButton("Go Home");
    private final JLabel createdRecipeLabel;

    private final UserProfileViewModel userProfileViewModel;
    private final ServiceManager serviceManager;
    private final UserProfileController userProfileController;

    public UserProfileView(UserProfileViewModel userProfileViewModel,
                           UserProfileController userProfileController,
                           ServiceManager serviceManager) {
        this.userProfileViewModel = userProfileViewModel;
        this.serviceManager = serviceManager;
        this.userProfileController = userProfileController;

        // setLayout(new BorderLayout());

        usernameLabel = new JLabel();
        createdRecipeLabel = new JLabel();

        userProfileViewModel.addPropertyChangeListener(this);

        goHomeButton.addActionListener(event -> {
            userProfileController.switchToHomePage();
        });

        add(goHomeButton);
        add(usernameLabel);
        add(createdRecipeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserProfileState state = (UserProfileState) evt.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return "account";
    }

    private void setFields(UserProfileState state) {
        final String username = state.getUsername();
        final List<Recipe> createdRecipe = state.getCreatedRecipes();

        usernameLabel.setText(username);

        createdRecipeLabel.setText("Created Recipes: " + createdRecipe.toString());
    }
}
