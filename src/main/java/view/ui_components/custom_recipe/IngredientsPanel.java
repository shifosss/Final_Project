package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IngredientsPanel extends JPanel {
    private final JPanel ingredientListPanel;

    public IngredientsPanel() {
        setLayout(new BorderLayout());
        ingredientListPanel = new JPanel();
        ingredientListPanel.setLayout(new BoxLayout(ingredientListPanel, BoxLayout.Y_AXIS));

        final JScrollPane scrollPane = new JScrollPane(ingredientListPanel);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        add(scrollPane, BorderLayout.CENTER);

        final JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(e -> addIngredientRow());
        add(addIngredientButton, BorderLayout.SOUTH);
    }

    private void addIngredientRow() {
        final JPanel ingredientRow = new JPanel(new GridLayout(1, 3, 5, 5));
        final JTextField nameField = new JTextField();
        final JTextField measurementField = new JTextField();
        final JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            ingredientListPanel.remove(ingredientRow);
            ingredientListPanel.revalidate();
            ingredientListPanel.repaint();
        });

        ingredientRow.add(nameField);
        ingredientRow.add(measurementField);
        ingredientRow.add(removeButton);
        ingredientListPanel.add(ingredientRow);
        ingredientListPanel.revalidate();
        ingredientListPanel.repaint();
    }

    public ArrayList<String[]> getIngredients() {
        ArrayList<String[]> ingredients = new ArrayList<>();
        for (Component component : ingredientListPanel.getComponents()) {
            JPanel row = (JPanel) component;
            JTextField nameField = (JTextField) row.getComponent(0);
            JTextField measurementField = (JTextField) row.getComponent(1);
            ingredients.add(new String[]{nameField.getText(), measurementField.getText()});
        }
        return ingredients;
    }
}
