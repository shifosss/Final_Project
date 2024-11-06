package view.ui_components;

import javax.swing.*;

/**
 * A panel that contains a label and text field.
 */
public class SearchPanel extends JPanel {
    public SearchPanel(JButton closeButton, JTextField textField, JButton searchButton) {
        this.add(closeButton);
        this.add(textField);
        this.add(searchButton);
    }
}
