package view.ui_components.user_profile;

import javax.swing.*;
import java.awt.*;

public class CustomRecipePanel extends JPanel {
    public CustomRecipePanel() {
        setBackground(new Color(211, 211, 211));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(169, 169, 169), 2),
                "Custom Recipe",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                Color.DARK_GRAY
        ));
    }
}
