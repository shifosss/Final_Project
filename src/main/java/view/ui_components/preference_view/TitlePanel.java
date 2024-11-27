package view.ui_components.preference_view;

import javax.swing.*;
import java.awt.*;

/**
 * TitlePanel is a reusable component for displaying a title in a JPanel.
 */
public class TitlePanel extends JPanel {

    public TitlePanel(String title, Color backgroundColor, Color textColor) {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding
        add(titleLabel, BorderLayout.CENTER);
    }
}
