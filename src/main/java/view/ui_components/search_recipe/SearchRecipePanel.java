package view.ui_components.search_recipe;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import view.ui_components.view_recipe.RecipeDetailPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    private static final int LEFT = 10;
    private static final int RIGHT = 10;
    private static final int FONT_SIZE = 14;

    // Colors for modern button styling
    private static final Color BUTTON_BACKGROUND = new Color(51, 122, 183);  // Nice blue
    private static final Color BUTTON_HOVER = new Color(40, 96, 144);        // Darker blue for hover
    private static final Color BUTTON_BORDER = new Color(46, 109, 164);      // Border blue
    private static final Color TEXT_COLOR = Color.WHITE;

    private JLabel imageLabel;
    private JButton nameButton;
    private final ServiceManager serviceManager;
    private Recipe currentRecipe;

    public SearchRecipePanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        // Sets Layout
        setLayout(new BorderLayout(H_GAP, V_GAP));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Initializes JComponents
        imageLabel = new JLabel();
        nameButton = createStyledButton();

        // Adjust the panel size
        setPreferredSize(new Dimension(200, 250));
    }

    private JButton createStyledButton() {
        JButton button = new JButton();

        // Basic button setup
        button.setFocusPainted(false);  // Remove focus border
        button.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_BACKGROUND);

        // Create rounded border with padding
        button.setBorder(new CompoundBorder(
                new LineBorder(BUTTON_BORDER, 1, true),  // Outer border, rounded
                new EmptyBorder(TOP, LEFT, BOTTOM, RIGHT)  // Inner padding
        ));

        // Make sure the background is painted
        button.setContentAreaFilled(true);
        button.setOpaque(true);

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BACKGROUND);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
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

        // Recipe name button at the bottom
        nameButton.setText(recipeName);
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