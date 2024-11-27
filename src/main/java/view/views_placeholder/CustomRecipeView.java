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
 * Custom recipe view.
 */
public class CustomRecipeView extends JPanel implements PageView<CustomRecipeState>,
        ActionListener, PropertyChangeListener {
    private final String viewName = "create recipe";

    private final JButton goHomeButton = new JButton("Go Home");
    private final JButton createRecipeButton = new JButton("Create Recipe");

    public CustomRecipeView(CustomRecipeViewModel customRecipeViewModel,
                            CustomRecipeController customRecipeController,
                            ServiceManager serviceManager) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        customRecipeViewModel.addPropertyChangeListener(this);

        // Panels
        final RecipeNamePanel recipeNamePanel = new RecipeNamePanel();
        final IngredientsPanel ingredientsPanel = new IngredientsPanel();
        final InstructionsPanel instructionsPanel = new InstructionsPanel();
        final AlcoholicPanel alcoholicPanel = new AlcoholicPanel();
        final ActionButtonPanel actionButtonPanel = new ActionButtonPanel(
                recipeNamePanel, ingredientsPanel, instructionsPanel, alcoholicPanel,
                customRecipeController);

        // Add components to layout
        add(recipeNamePanel, BorderLayout.NORTH);
        add(ingredientsPanel, BorderLayout.CENTER);
        add(instructionsPanel, BorderLayout.SOUTH);
        add(alcoholicPanel, BorderLayout.WEST);
        add(actionButtonPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final CustomRecipeState state = (CustomRecipeState) evt.getSource();
            setFields(state);
        }
        else if (evt.getPropertyName().equals("successful creation")) {
            JOptionPane.showMessageDialog(this, "Successfully created recipe");
        }
    }

    private void setFields(CustomRecipeState state) {

    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(CustomRecipeState state) {

    }
}