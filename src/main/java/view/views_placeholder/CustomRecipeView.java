package view.views_placeholder;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeState;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.ui_components.custom_recipe.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * custom recipe view
 */
public class CustomRecipeView extends JPanel implements PageView<CustomRecipeState>,
        ActionListener, PropertyChangeListener {
    private final String viewName = "create recipe";
    // initialize panel
    private final CustomIngredientsPanel ingredientsPanel;

    // initialize other panel
    private final CustomRecipeNamePanel namePanel;
    private final CustomAlcoholOptionPanel alcoholOptionPanel;

    private final JButton goHomeButton = new JButton("Go Home");
    private final JButton createRecipeButton = new JButton("Create Recipe");

    public CustomRecipeView(CustomRecipeViewModel customRecipeViewModel,
                            CustomRecipeController customRecipeController,
                            ServiceManager serviceManager) {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 165, 0));

        // top title
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(255, 165, 0));
        final JLabel titleLabel = new JLabel("Create Your Custom Recipe");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(goHomeButton);
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // middle area
        final JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 165, 0));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // ingredients name
        namePanel = new CustomRecipeNamePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(namePanel, gbc);

        // dynamic ingredients panel
        ingredientsPanel = new CustomIngredientsPanel(customRecipeController, customRecipeViewModel);
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

        goHomeButton.addActionListener(event -> {
            customRecipeController.switchToHome();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(CustomRecipeState state) {

    }
}