package view;

import data_access.MealDataAccessObject;
import entities.recipe.factory.MealFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class viewRecipe implements ActionListener {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    public static void main(String[] args) {
        new viewRecipe();
    }

    public viewRecipe() {

        final MealFactory mealFactory = new MealFactory();
        MealDataAccessObject mealDataAccessObject = new MealDataAccessObject(mealFactory);
        frame.setTitle("Meal Recipe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setVisible(true);
        for (int i = 0; i < mealDataAccessObject.get_length_meals(i); i++) {
            panel.add(createbutton(i));
        }
        frame.setVisible(true);
    }

    public static JButton createbutton(int i) {
        final MealFactory mealFactory = new MealFactory();
        MealDataAccessObject mealDataAccessObject = new MealDataAccessObject(mealFactory);
        JButton button = new JButton(mealDataAccessObject.getMealName(i));
        button.setSize(90, 190);
        button.setBackground(null);
        button.setForeground(Color.BLACK);
        ImageIcon image = new ImageIcon(Objects.requireNonNull((String.format("/h/u11/c3/00/uppalshy/IdeaProjects/lab10/src/main/java/view/image/image%d.jpg", i + 1))));
        Image image1 = image.getImage();
        Image image2 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image2));
        button.setVisible(true);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
