package view.ui_components.user_profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReturnButtonPanel extends JPanel {

    public ReturnButtonPanel(JButton returnButton) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(211, 211, 211));

        returnButton.setBackground(new Color(105, 105, 105));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.setBorderPainted(false);
        returnButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(returnButton);
    }
}
