package view.ui_components.recipe_detail;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the back button and bookmark button.
 */
public class NavigationActionPanel extends JPanel {
    public NavigationActionPanel(JButton backButton, JButton bookmarkButton) {
        setLayout(new BorderLayout());

        // Add buttons to their respective positions
        add(backButton, BorderLayout.WEST);
        add(bookmarkButton, BorderLayout.EAST);
    }
}
