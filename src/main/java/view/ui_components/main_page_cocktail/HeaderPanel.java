package view.ui_components.main_page_cocktail;

import javax.swing.*;
import java.awt.*;

/**
 * Header Panel of the main page view.
 */
public class HeaderPanel extends JPanel {

    public HeaderPanel(String title, Component additionalComponent) {
        setLayout(new BorderLayout());

        // Title label
        final JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.CENTER);

        // Add additional component (e.g., button) to the right
        if (additionalComponent != null) {
            add(additionalComponent, BorderLayout.EAST);
        }
    }
}
