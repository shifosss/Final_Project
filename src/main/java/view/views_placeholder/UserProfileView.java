package view.views_placeholder;

import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
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

public class UserProfileView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
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

        // user name
        userIconPanel = new UserIconPanel("");
        add(userIconPanel, BorderLayout.NORTH);

        // custom recipe panel
        customRecipePanel = new CustomRecipePanel();
        add(customRecipePanel, BorderLayout.CENTER);

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

    }

    @Override
    public String getViewName() {
        return viewName;
    }
}