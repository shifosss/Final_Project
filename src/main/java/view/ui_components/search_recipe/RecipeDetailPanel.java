package view.ui_components.search_recipe;

import entities.recipe.Recipe;
import entities.recipe.Ingredient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RecipeDetailPanel extends JPanel {
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 800;
    private static final int PADDING = 20;
    private static final int TITLE_FONT_SIZE = 24;
    private static final int HEADER_FONT_SIZE = 18;
    private static final int CONTENT_FONT_SIZE = 14;
    private static final int STEP_NUMBER_SIZE = 16;

    // Colors for modern look
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color HEADER_COLOR = new Color(33, 37, 41);
    private static final Color TEXT_COLOR = new Color(73, 80, 87);
    private static final Color INGREDIENT_BG = new Color(233, 236, 239);
    private static final Color STEP_NUMBER_COLOR = new Color(51, 122, 183);  // Blue for step numbers
    private static final Color STEP_BG = new Color(255, 255, 255);  // White background for steps

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

        // Instructions with steps
        JPanel instructionsPanel = createInstructionsPanel(recipe.getInstruction());
        instructionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(instructionsPanel);

        // Add the content panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
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
                panel.add(Box.createVerticalStrut(5));
            }
        }

        return panel;
    }

    private JPanel createIngredientItemPanel(Ingredient ingredient) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(INGREDIENT_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(INGREDIENT_BG, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        String measureText = ingredient.getMeasure().trim().isEmpty() ?
                ingredient.getName() :
                ingredient.getMeasure() + " " + ingredient.getName();

        JLabel ingredientLabel = new JLabel(measureText);
        ingredientLabel.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        ingredientLabel.setForeground(TEXT_COLOR);

        panel.add(ingredientLabel, BorderLayout.WEST);
        panel.setMaximumSize(new Dimension(PANEL_WIDTH - 40, 40));
        return panel;
    }

    private JPanel createStepPanel(int stepNumber, String instruction) {
        JPanel panel = new JPanel(new BorderLayout(15, 0));  // Increased gap for better spacing
        panel.setBackground(STEP_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230), 1, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        // Create left panel for step number with FlowLayout for better alignment
        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        numberPanel.setBackground(STEP_BG);

        // Step number with fixed width
        JLabel numberLabel = new JLabel(String.format("%d.", stepNumber));
        numberLabel.setFont(new Font("SansSerif", Font.BOLD, STEP_NUMBER_SIZE));
        numberLabel.setForeground(STEP_NUMBER_COLOR);
        numberLabel.setPreferredSize(new Dimension(30, numberLabel.getPreferredSize().height));
        numberPanel.add(numberLabel);

        // Instruction text panel
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(STEP_BG);

        // Multi-line label for instruction text
        JLabel instructionLabel = new JLabel("<html><div style='width: " +
                (PANEL_WIDTH - 120) + "px'>" + instruction + "</div></html>");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
        instructionLabel.setForeground(TEXT_COLOR);
        instructionLabel.setVerticalAlignment(JLabel.TOP);  // Align text to top

        textPanel.add(instructionLabel, BorderLayout.CENTER);

        // Add components to main panel
        panel.add(numberPanel, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);

        // Let the panel calculate its preferred height based on content
        panel.setMaximumSize(new Dimension(PANEL_WIDTH - 40, panel.getPreferredSize().height));

        return panel;
    }

    private JPanel createInstructionsPanel(String instructions) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);

        // Split instructions by period and clean up
        String[] steps = instructions.split("\\.");
        steps = Arrays.stream(steps)
                .map(String::trim)
                .filter(step -> !step.isEmpty())
                .toArray(String[]::new);

        // Create panel for each step
        for (int i = 0; i < steps.length; i++) {
            if (!steps[i].isEmpty()) {
                JPanel stepPanel = createStepPanel(i + 1, steps[i]);
                panel.add(stepPanel);
                // Add space between steps, smaller space if it's the last step
                if (i < steps.length - 1) {
                    panel.add(Box.createVerticalStrut(10));
                }
            }
        }

        // Add some padding at the bottom of all steps
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }
}