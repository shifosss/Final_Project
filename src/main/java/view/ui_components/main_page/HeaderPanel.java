package view.ui_components.main_page;

import javax.swing.*;
import java.awt.*;

/**
 * Header Panel of the main page view.
 */
public class HeaderPanel extends JPanel {

    public HeaderPanel(JButton searchButton, JButton exploreIngredientButton,
                       JButton customRecipeButton, JButton userButton) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        add(exploreIngredientButton);
        add(searchButton);
        add(customRecipeButton);
        add(userButton);
    }
}
