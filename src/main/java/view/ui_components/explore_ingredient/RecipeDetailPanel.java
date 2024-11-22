package view.ui_components.explore_ingredient;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

import javax.swing.*;
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

    // New fields for navigation
    private Recipe currentRecipe;
    private RecipeScrollPanel scrollPanel;
    private JButton nextButton;

    public RecipeDetailPanel(Recipe recipe, RecipeScrollPanel scrollPanel) {
        super(new BorderLayout());
        this.currentRecipe = recipe;
        this.scrollPanel = scrollPanel;

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        setBackground(BACKGROUND_COLOR);

        // Create main content panel with vertical layout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(null); // Remove any default border

        // Title section
        addTitle(contentPanel, recipe.getName());

        // Image section
        addImage(contentPanel, recipe.getImageLink());

        // Ingredients section with proper alignment
        addIngredients(contentPanel, recipe.getIngredients());

        // Instructions section with proper alignment
        addInstructions(contentPanel, recipe.getInstruction());

        // Add navigation panel
        addNavigationPanel(contentPanel);

        // Add the content panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addNavigationPanel(JPanel contentPanel) {
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navigationPanel.setBackground(BACKGROUND_COLOR);
        navigationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        nextButton = new JButton("Next Recipe →");
        nextButton.setFont(new Font("SansSerif", Font.BOLD, CONTENT_FONT_SIZE));
        nextButton.setForeground(HEADER_COLOR);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Style the button
        nextButton.setBackground(new Color(240, 240, 240));
        nextButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        nextButton.addActionListener(e -> showNextRecipe());

        navigationPanel.add(nextButton);
        contentPanel.add(navigationPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Initially check if next recipe exists
        updateNextButtonState();
    }

    private void addTitle(JPanel contentPanel, String title) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setForeground(HEADER_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));
    }

    private void addImage(JPanel contentPanel, String imageLink) {
        ImageIcon image = new ImageIcon(imageLink);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(imageLabel);
        contentPanel.add(Box.createVerticalStrut(20));
    }

    private void addIngredients(JPanel contentPanel, List<Ingredient> ingredients) {
        // Ingredients header
        JLabel ingredientsHeader = new JLabel("Ingredients");
        ingredientsHeader.setFont(new Font("SansSerif", Font.BOLD, HEADER_FONT_SIZE));
        ingredientsHeader.setForeground(HEADER_COLOR);
        ingredientsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(ingredientsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        // Create container for ingredients with zero insets
        JPanel ingredientsContainer = new JPanel();
        ingredientsContainer.setLayout(new BoxLayout(ingredientsContainer, BoxLayout.Y_AXIS));
        ingredientsContainer.setBackground(BACKGROUND_COLOR);
        ingredientsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientsContainer.setBorder(null);

        int ingredientNumber = 1;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName() != null && !ingredient.getName().trim().isEmpty()) {
                // Create ingredient item with zero left margin
                JPanel ingredientPanel = new JPanel(new BorderLayout(10, 0));
                ingredientPanel.setBackground(INGREDIENT_BG);
                ingredientPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 12));
                ingredientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Add number label
                JLabel numberLabel = new JLabel(String.format("%d.", ingredientNumber));
                numberLabel.setFont(new Font("SansSerif", Font.BOLD, STEP_NUMBER_SIZE));
                numberLabel.setForeground(STEP_NUMBER_COLOR);
                numberLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

                // Create ingredient text
                String measureText = ingredient.getMeasure().trim().isEmpty() ?
                        ingredient.getName() :
                        ingredient.getMeasure() + " " + ingredient.getName();

                JLabel ingredientLabel = new JLabel(measureText);
                ingredientLabel.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
                ingredientLabel.setForeground(TEXT_COLOR);

                // Add number and text to panel
                JPanel numberContainer = new JPanel(new BorderLayout());
                numberContainer.setBackground(INGREDIENT_BG);
                numberContainer.add(numberLabel, BorderLayout.WEST);

                ingredientPanel.add(numberContainer, BorderLayout.WEST);
                ingredientPanel.add(ingredientLabel, BorderLayout.CENTER);
                ingredientPanel.setMaximumSize(new Dimension(PANEL_WIDTH - 2 * PADDING, 40));

                ingredientsContainer.add(ingredientPanel);
                if (ingredientNumber < ingredients.size()) {
                    ingredientsContainer.add(Box.createVerticalStrut(5));
                }

                ingredientNumber++;
            }
        }

        contentPanel.add(ingredientsContainer);
        contentPanel.add(Box.createVerticalStrut(20));
    }

    private void addInstructions(JPanel contentPanel, String instructions) {
        // Instructions header
        JLabel instructionsHeader = new JLabel("Instructions");
        instructionsHeader.setFont(new Font("SansSerif", Font.BOLD, HEADER_FONT_SIZE));
        instructionsHeader.setForeground(HEADER_COLOR);
        instructionsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(instructionsHeader);
        contentPanel.add(Box.createVerticalStrut(10));

        // Create container for instructions with zero insets
        JPanel instructionsContainer = new JPanel();
        instructionsContainer.setLayout(new BoxLayout(instructionsContainer, BoxLayout.Y_AXIS));
        instructionsContainer.setBackground(BACKGROUND_COLOR);
        instructionsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionsContainer.setBorder(null);

        String[] steps = instructions.split("\\.");
        steps = Arrays.stream(steps)
                .map(String::trim)
                .filter(step -> !step.isEmpty())
                .toArray(String[]::new);

        for (int i = 0; i < steps.length; i++) {
            if (!steps[i].isEmpty()) {
                // Create step panel with zero left margin
                JPanel stepPanel = new JPanel(new BorderLayout(10, 0));
                stepPanel.setBackground(STEP_BG);
                stepPanel.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 12));
                stepPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // Step number
                JLabel numberLabel = new JLabel(String.format("%d.", i + 1));
                numberLabel.setFont(new Font("SansSerif", Font.BOLD, STEP_NUMBER_SIZE));
                numberLabel.setForeground(STEP_NUMBER_COLOR);
                numberLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

                // Step text
                JLabel instructionLabel = new JLabel("<html><div style='width: " +
                        (PANEL_WIDTH - 80) + "px'>" + steps[i] + "</div></html>");
                instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, CONTENT_FONT_SIZE));
                instructionLabel.setForeground(TEXT_COLOR);

                stepPanel.add(numberLabel, BorderLayout.WEST);
                stepPanel.add(instructionLabel, BorderLayout.CENTER);
                stepPanel.setMaximumSize(new Dimension(PANEL_WIDTH - 2 * PADDING,
                        stepPanel.getPreferredSize().height));

                instructionsContainer.add(stepPanel);
                if (i < steps.length - 1) {
                    instructionsContainer.add(Box.createVerticalStrut(10));
                }
            }
        }

        contentPanel.add(instructionsContainer);
        contentPanel.add(Box.createVerticalStrut(20));
    }

    private void showNextRecipe() {
        Recipe nextRecipe = scrollPanel.getNextRecipe(currentRecipe);
        if (nextRecipe != null) {
            // Update the current panel with the next recipe
            this.currentRecipe = nextRecipe;

            // Remove all components and re-add with new recipe
            this.removeAll();

            // Create main content panel with vertical layout
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(BACKGROUND_COLOR);
            contentPanel.setBorder(null);

            // Re-add all components with new recipe data
            addTitle(contentPanel, nextRecipe.getName());
            addImage(contentPanel, nextRecipe.getImageLink());
            addIngredients(contentPanel, nextRecipe.getIngredients());
            addInstructions(contentPanel, nextRecipe.getInstruction());
            addNavigationPanel(contentPanel);

            // Add the content panel to a scroll pane
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

            add(scrollPane, BorderLayout.CENTER);

            // Update UI
            revalidate();
            repaint();

            // Reset scroll position to top
            SwingUtilities.invokeLater(() -> {
                scrollPane.getVerticalScrollBar().setValue(0);
            });
        }

        updateNextButtonState();
    }

    private void updateNextButtonState() {
        Recipe nextRecipe = scrollPanel.getNextRecipe(currentRecipe);
        nextButton.setEnabled(nextRecipe != null);
        nextButton.setToolTipText(nextRecipe == null ? "No more recipes" : "View next recipe");
    }
}