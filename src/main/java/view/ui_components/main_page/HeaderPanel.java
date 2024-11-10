package view.ui_components.main_page;

import javax.swing.*;
import java.awt.*;

/**
 * Header Panel of the main page view.
 */
public class HeaderPanel extends JPanel {

    public HeaderPanel(String title) {
        setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.CENTER);
    }
}
