package view.ui_components;

import domain.entities.recipe.Recipe;

import javax.swing.*;
import java.awt.*;

/**
 * Recipe Panel that shows when searching for recipes.
 */
public class SearchRecipePanel extends JPanel {
    private static final int H_GAP = 10;
    private static final int V_GAP = 10;
    private static final int TOP = 5;
    private static final int BOTTOM = 5;
    private static final int LEFT = 0;
    private static final int RIGHT = 0;
    private static final int FONT_SIZE = 14;

    private final JLabel nameLabel;
    private final JLabel imageLabel;

    public SearchRecipePanel(Recipe recipe) {
        setLayout(new BorderLayout(H_GAP, V_GAP));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        final String recipeName = recipe.getName();
        final String imageLink = recipe.getImageLink();

        final ImageIcon recipeImage = getImageFromUrl(imageLink);

        // Image label at the top
        imageLabel = new JLabel();
        imageLabel.setIcon(recipeImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Recipe name label at the bottom
        nameLabel = new JLabel(recipeName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(TOP, LEFT, BOTTOM, RIGHT));
        add(nameLabel, BorderLayout.SOUTH);

        // Adjust the panel size
        setPreferredSize(new Dimension(200, 250));
    }

    private ImageIcon getImageFromUrl(String imageUrl) {
        return getImageIcon(imageUrl);
    }
}