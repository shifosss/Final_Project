package view.ui_components.search_recipe;

import entities.recipe.Recipe;
import entities.recipe.Ingredient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class RecipeDetailPanel extends JPanel {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 800;
    private static final int PADDING = 20;
    private static final int TITLE_FONT_SIZE = 24;
    private static final int HEADER_FONT_SIZE = 18;
    private static final int CONTENT_FONT_SIZE = 14;

    // Colors for modern look
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color HEADER_COLOR = new Color(33, 37, 41);
    private static final Color TEXT_COLOR = new Color(73, 80, 87);
    private static final Color INGREDIENT_BG = new Color(233, 236, 239);

    public RecipeDetailPanel(Recipe recipe) {
        super(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setBackground(BACKGROUND_COLOR);

        // Create main content panel with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);

        // Title
        JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(HEADER_COLOR);
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
        ingredientsHeader.setForeground(HEADER_COLOR);
        ingredientsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(ingredientsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        // Create ingredients panel with modern styling
        JPanel ingredientsPanel = createIngredientsPanel(recipe.getIngredients());
        ingredientsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(ingredientsPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Instructions Section
        JLabel instructionsHeader = new JLabel("Instructions");
        instructionsHeader.setFont(new Font("SansSerif", Font.BOLD, HEADER_FONT_SIZE));
        instructionsHeader.setForeground(HEADER_COLOR);
        instructionsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(instructionsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        // Instructions with modern styling
        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        instructionsArea.setForeground(TEXT_COLOR);
        instructionsArea.setBackground(BACKGROUND_COLOR);
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
        scrollPane.setBorder(null); // Remove border for modern look
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createIngredientsPanel(List<Ingredient> ingredients) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName() != null && !ingredient.getName().trim().isEmpty()) {
                JPanel ingredientPanel = createIngredientItemPanel(ingredient);
                panel.add(ingredientPanel);
                panel.add(Box.createVerticalStrut(5)); // Space between ingredients
            }
        }

        return panel;
    }

    private JPanel createIngredientItemPanel(Ingredient ingredient) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(INGREDIENT_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // Create rounded border effect
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INGREDIENT_BG, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Ingredient name with measurement
        String measureText = ingredient.getMeasure().trim().isEmpty() ?
                ingredient.getName() :
                ingredient.getMeasure() + " " + ingredient.getName();

        JLabel ingredientLabel = new JLabel(measureText);
        ingredientLabel.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        ingredientLabel.setForeground(TEXT_COLOR);

        panel.add(ingredientLabel, BorderLayout.WEST);

        // Make the panel not expand full width
        panel.setMaximumSize(new Dimension(PANEL_WIDTH - 40, 40));
        return panel;
    }
}