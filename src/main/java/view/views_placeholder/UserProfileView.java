package view.views_placeholder;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import view.PageView;
import view.ui_components.user_profile.UserIconPanel;
import view.ui_components.user_profile.CustomRecipePanel;
import view.ui_components.user_profile.ReturnButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class UserProfileView extends JPanel implements PageView<UserProfileState>, ActionListener, PropertyChangeListener {
    private final String viewName = "account";

    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ServiceManager serviceManager;

    private final UserIconPanel userIconPanel;
    private final CustomRecipePanel customRecipePanel;
    private final ReturnButtonPanel returnButtonPanel;
    private final JButton returnButton = new JButton("Return to home.");

    public UserProfileView(UserProfileViewModel userProfileViewModel,
                           UserProfileController userProfileController,
                           ServiceManager serviceManager) {
        this.userProfileController = userProfileController;
        this.serviceManager = serviceManager;
        this.userProfileViewModel = userProfileViewModel;

        setLayout(new BorderLayout());
        setBackground(new Color(211, 211, 211));

        userProfileViewModel.addPropertyChangeListener(this);

        // user name
        userIconPanel = new UserIconPanel();
        add(userIconPanel, BorderLayout.NORTH);

        // custom recipe panel
        customRecipePanel = new CustomRecipePanel(
                userProfileViewModel, userProfileController, serviceManager);
        add(customRecipePanel.getScrollPane(), BorderLayout.CENTER);

        // back to homepage button
        returnButtonPanel = new ReturnButtonPanel(returnButton);
        returnButton.addActionListener(event -> {
            userProfileController.switchToHomePage();
        });
        add(returnButtonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserProfileState state = (UserProfileState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(UserProfileState state) {
        final String username = state.getUsername();
        final List<Recipe> createdrecipes = state.getCreatedRecipes();
        userIconPanel.updateComponents(username);
        customRecipePanel.updateComponents(createdrecipes);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(UserProfileState state) {

    }
}