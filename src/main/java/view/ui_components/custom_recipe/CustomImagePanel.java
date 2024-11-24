package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;

public class CustomImagePanel extends JPanel {
    public CustomImagePanel() {
        setBackground(new Color(255, 165, 0));
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon cocktailImage = new ImageIcon(getClass().getResource("/image/cocktail1.png"));
        Image scaledImage = cocktailImage.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        add(imageLabel);
    }
}