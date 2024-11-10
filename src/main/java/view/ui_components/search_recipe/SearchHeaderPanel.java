package view.ui_components.search_recipe;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that contains a back button, search field, and search button.
 */
public class SearchHeaderPanel extends JPanel {
    private final JButton backButton;
    private final JTextField searchField;
    private final JButton searchButton;

    public SearchHeaderPanel(JButton backButton, JTextField searchField,
                             JButton searchButton) {
        this.backButton = backButton;
        this.searchField = searchField;
        this.searchButton = searchButton;

        setLayout(new BorderLayout());

        // Add components
        add(backButton, BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
}