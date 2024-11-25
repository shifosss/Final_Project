package view.ViewPlaceholder;

import view.ui_components.custom_recipe.*;
import interface_adapter.custom_recipe.CustomIngredientsController;
import interface_adapter.custom_recipe.CustomIngredientsViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * custom recipe view
 */
public class CustomRecipeView extends JPanel {
    CustomIngredientsViewModel viewModel = new CustomIngredientsViewModel();
    CustomIngredientsController controller = new CustomIngredientsController(viewModel);

    // initialize panel
    CustomIngredientsPanel ingredientsPanel = new CustomIngredientsPanel(controller, viewModel);

    // initialize other panel
    CustomRecipeNamePanel namePanel = new CustomRecipeNamePanel();
    CustomAlcoholOptionPanel alcoholOptionPanel = new CustomAlcoholOptionPanel();
    CustomImagePanel imagePanel = new CustomImagePanel();

    public CustomRecipeView() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 165, 0));

        // top title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(255, 165, 0));
        JLabel titleLabel = new JLabel("Create Your Custom Recipe");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // middle area
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 165, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // ingredients name
        namePanel = new CustomRecipeNamePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(namePanel, gbc);

        // dynamic ingredients panel
        ingredientsPanel = new CustomIngredientsPanel(controller, viewModel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(ingredientsPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // right side image
        add(new CustomImagePanel(), BorderLayout.EAST);

        // bottom Alcoholic option
        alcoholOptionPanel = new CustomAlcoholOptionPanel();
        add(alcoholOptionPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test CustomRecipeView");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new CustomRecipeView());
            frame.setVisible(true);
        });
    }
}