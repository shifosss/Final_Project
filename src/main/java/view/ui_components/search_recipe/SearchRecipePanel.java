package view.ui_components.search_recipe;

import domain.entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private JButton nameButton;
    private JLabel imageLabel;
    private final ServiceManager serviceManager;

    private Recipe currentRecipe;

    public SearchRecipePanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        // Sets Layout.
        setLayout(new BorderLayout(H_GAP, V_GAP));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        // Initializes image label
        imageLabel = new JLabel();
        // Initializes name button
        nameButton = new JButton();
        nameButton.setBorderPainted(false);  // Make it look like a label
        nameButton.setContentAreaFilled(false);
        nameButton.setFocusPainted(false);
        nameButton.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        // Add listener to name button
        nameButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                nameButton.setForeground(Color.BLUE);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                nameButton.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        // Adjust the panel size
        setPreferredSize(new Dimension(200, 250));
    }

    /**
     * Shows the given recipe thumbnail in the panel.
     * @param recipe Recipe entity that holds the recipe information.
     */
    public void addRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        final String recipeName = recipe.getName();
        final String imageLink = recipe.getImageLink();

        final ImageServiceInterface imageService = serviceManager.getWebImageService();
        final ImageIcon recipeImage = imageService.fetchImage(imageLink);

        // Image label at the top
        imageLabel.setIcon(recipeImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Recipe name label at the bottom
        nameButton.setText(recipeName);
        nameButton.setHorizontalAlignment(JButton.CENTER);
        nameButton.addActionListener(e -> showRecipeDetails());
        add(nameButton, BorderLayout.SOUTH);
    }

    private void showRecipeDetails() {
        JFrame detailFrame = new JFrame("Recipe Details");
        RecipeDetailPanel detailPanel = new RecipeDetailPanel(currentRecipe);
        detailFrame.add(detailPanel);
        detailFrame.pack();
        detailFrame.setLocationRelativeTo(this);
        detailFrame.setVisible(true);
    }
}