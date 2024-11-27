package view.ui_components.preference_view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IngredientPanel creates a grid of toggle buttons for ingredients.
 */
public class IngredientPanel extends JPanel {

    private final List<JToggleButton> ingredientButtons;

    public IngredientPanel(List<String> ingredients, Color backgroundColor,
                           Color borderColor, Color selectedColor, Color unselectedColor) {
        this.ingredientButtons = new ArrayList<>();
        setLayout(new GridLayout(0, 3, 15, 15)); // 3 columns, spacing
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        for (String ingredient : ingredients) {
            JToggleButton toggleButton = new JToggleButton(ingredient);
            toggleButton.setBackground(unselectedColor); // Default background
            toggleButton.setForeground(Color.WHITE);
            toggleButton.setFocusPainted(false);
            toggleButton.setBorder(new LineBorder(borderColor, 2, true)); // Border color
            toggleButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
            toggleButton.setOpaque(true);

            // Change background color when selected/deselected
            toggleButton.addActionListener(e -> {
                if (toggleButton.isSelected()) {
                    toggleButton.setBackground(selectedColor); // Bright color for selected
                } else {
                    toggleButton.setBackground(unselectedColor); // Default color for unselected
                }
            });

            ingredientButtons.add(toggleButton);
            add(toggleButton);
        }
    }
    public List<String> getSelectedIngredients() {
        List<String> selectedIngredients = new ArrayList<>();
        for (JToggleButton button : ingredientButtons) {
            if (button.isSelected()) {
                selectedIngredients.add(button.getText());
            }
        }
        return selectedIngredients;
    }
}
