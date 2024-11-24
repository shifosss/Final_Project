package view.ViewPlaceholder;

import view.ui_components.user_profile.UserIconPanel;
import view.ui_components.user_profile.CustomRecipePanel;
import view.ui_components.user_profile.ReturnButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class UserProfileView extends JPanel {
    public UserProfileView(String username, ActionListener returnToHomepageListener) {
        setLayout(new BorderLayout());
        setBackground(new Color(211, 211, 211));

        // user name
        UserIconPanel userIconPanel = new UserIconPanel(username);
        add(userIconPanel, BorderLayout.NORTH);

        // custom recipe panel
        CustomRecipePanel customRecipePanel = new CustomRecipePanel();
        add(customRecipePanel, BorderLayout.CENTER);

        // back to homepage button
        ReturnButtonPanel returnButtonPanel = new ReturnButtonPanel(
                "Return to Homepage", returnToHomepageListener);
        add(returnButtonPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Profile");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new UserProfileView("JohnDoe", e -> System.out.println("Back to homepage!")));
            frame.setVisible(true);
        });
    }
}