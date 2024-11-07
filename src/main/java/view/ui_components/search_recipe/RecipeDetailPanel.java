package view.ui_components.search_recipe;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel that shows the detailed information of a recipe.
 */
public class RecipeDetailPanel extends JPanel {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 800;
    private static final int PADDING = 20;
    private static final int TITLE_FONT_SIZE = 24;
    private static final int HEADER_FONT_SIZE = 18;
    private static final int CONTENT_FONT_SIZE = 14;

    public RecipeDetailPanel(Recipe recipe) {
        super(new BorderLayout());  // Call superclass constructor with layout
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        // Create main content panel with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Image
        ImageIcon image = new ImageIcon(recipe.getImageLink());
        JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(imageLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Ingredients Section
        JLabel ingredientsHeader = new JLabel("Ingredients");
        ingredientsHeader.setFont(new Font("SansSerif", Font.BOLD, HEADER_FONT_SIZE));
        ingredientsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(ingredientsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        ingredientsArea.setEditable(false);
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setText(formatIngredients(recipe));
        ingredientsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(ingredientsArea);
        contentPanel.add(Box.createVerticalStrut(20));

        // Instructions Section
        JLabel instructionsHeader = new JLabel("Instructions");
        instructionsHeader.setFont(new Font("SansSerif", Font.BOLD, HEADER_FONT_SIZE));
        instructionsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(instructionsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        instructionsArea.setEditable(false);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setText(recipe.getInstruction());
        instructionsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(instructionsArea);

        // Add the content panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private String formatIngredients(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : recipe.getIngredients()) {
            sb.append("• ").append(ingredient).append("\n");
        }
        return sb.toString();
    }
}