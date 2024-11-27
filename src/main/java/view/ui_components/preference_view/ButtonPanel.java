package view.ui_components.preference_view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * ButtonPanel creates a panel with "Save" and "Back to Home" buttons.
 */
public class ButtonPanel extends JPanel {

    public ButtonPanel(ActionListener saveAction, ActionListener backAction,
                       Color saveButtonColor, Color backButtonColor) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(30, 30, 30)); // Slightly darker gray
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton saveButton = new JButton("Save Preferences");
        styleButton(saveButton, saveButtonColor);
        saveButton.addActionListener(saveAction);
        add(saveButton);

        JButton backButton = new JButton("Back to Home");
        styleButton(backButton, backButtonColor);
        backButton.addActionListener(backAction);
        add(backButton);
    }
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor
    }
}
