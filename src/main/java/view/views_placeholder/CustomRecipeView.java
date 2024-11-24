package view.views_placeholder;

import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipeState;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.PageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Custom recipe creation view.
 */
public class CustomRecipeView extends JPanel implements
        PageView, ActionListener, PropertyChangeListener {
    private final CustomRecipeController customRecipeController;
    private final CustomRecipeViewModel customRecipeViewModel;
    private final ServiceManager serviceManager;

    private final JButton goBackButton = new JButton("Go Back");

    public CustomRecipeView(CustomRecipeViewModel customRecipeViewModel,
                            CustomRecipeController customRecipeController,
                            ServiceManager serviceManager) {
        this.customRecipeController = customRecipeController;
        this.customRecipeViewModel = customRecipeViewModel;
        this.serviceManager = serviceManager;

        this.customRecipeViewModel.addPropertyChangeListener(this);

        goBackButton.addActionListener(event -> customRecipeController.switchToHome());

        add(goBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CustomRecipeState state = (CustomRecipeState) evt.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return "create recipe";
    }

    private void setFields(CustomRecipeState state) {

    }
}
