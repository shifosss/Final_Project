package view.ui_components.custom_recipe;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CustomRecipeNamePanel extends JPanel {
    private final JTextField cocktailNameField;

    public CustomRecipeNamePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(255, 165, 0));
        setBorder(createRoundedBorder("Cocktail Name", new Color(255, 87, 34)));

        cocktailNameField = new JTextField(20);
        add(cocktailNameField);
    }

    public String getCocktailName() {
        return cocktailNameField.getText();
    }

    private CompoundBorder createRoundedBorder(String title, Color color) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12),
                Color.WHITE
        );
        return BorderFactory.createCompoundBorder(
                titledBorder,
                new EmptyBorder(10, 10, 10, 10)
        );
    }
}
