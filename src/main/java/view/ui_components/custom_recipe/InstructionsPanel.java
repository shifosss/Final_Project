package view.ui_components.custom_recipe;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    private final JTextArea instructionsArea;

    public InstructionsPanel() {
        setLayout(new BorderLayout());
        final JLabel label = new JLabel("Instructions:");
        instructionsArea = new JTextArea(5, 30);
        final JScrollPane scrollPane = new JScrollPane(instructionsArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getInstructions() {
        return instructionsArea.getText();
    }
}
