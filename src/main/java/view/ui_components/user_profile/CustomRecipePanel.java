package view.ui_components.user_profile;

import entities.recipe.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomRecipePanel extends JPanel {

    private final JScrollPane scrollPane;

    public CustomRecipePanel() {
        setBackground(new Color(211, 211, 211));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(169, 169, 169), 2),
                "Custom Recipe",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                Color.DARK_GRAY
        ));

        scrollPane = new JScrollPane();
    }

    /**
     * Updates the custom recipe panel which consists of a list of recipes the user has created.
     * @param createdRecipes the list of recipes created by user.
     */
    public void addComponents(List<Recipe> createdRecipes) {

    }
}
