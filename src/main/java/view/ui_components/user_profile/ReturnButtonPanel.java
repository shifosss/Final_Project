package view.ui_components.user_profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReturnButtonPanel extends JPanel {

    public ReturnButtonPanel(String buttonText, ActionListener listener) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(211, 211, 211));

        JButton homeButton = new JButton(buttonText);
        homeButton.setBackground(new Color(105, 105, 105));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        homeButton.addActionListener(listener);

        add(homeButton);
    }
}
