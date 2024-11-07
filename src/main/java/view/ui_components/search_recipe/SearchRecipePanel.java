package view.ui_components.search_recipe;

import domain.entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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

    private JLabel nameLabel;
    private JLabel imageLabel;
    private final ServiceManager serviceManager;

    public SearchRecipePanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        // Sets Layout.
        setLayout(new BorderLayout(H_GAP, V_GAP));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        // Initializes JComponents
        imageLabel = new JLabel();
        nameLabel = new JLabel();
        // Adjust the panel size
        setPreferredSize(new Dimension(200, 250));
    }

    /**
     * Shows the given recipe thumbnail in the panel.
     * @param recipe Recipe entity that holds the recipe information.
     */
    public void addRecipe(Recipe recipe) {
        final String recipeName = recipe.getName();
        final String imageLink = recipe.getImageLink();

        final ImageServiceInterface imageService = serviceManager.getWebImageService();
        final ImageIcon recipeImage = imageService.fetchImage(imageLink);

        // Image label at the top
        imageLabel.setIcon(recipeImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Recipe name label at the bottom
        nameLabel.setText(recipeName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(TOP, LEFT, BOTTOM, RIGHT));
        add(nameLabel, BorderLayout.SOUTH);
    }
}