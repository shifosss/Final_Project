package view;

import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.services.ServiceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Recipe Detail View that shows the information about a selected recipe.
 */
public class RecipeDetailView extends JPanel implements
        PageView, ActionListener, PropertyChangeListener {
    private final String view_name = "recipe detail";

    private final RecipeDetailViewModel recipeDetailViewModel;
    private final ServiceManager serviceManager;
    private final RecipeDetailController recipeDetailController;

    public RecipeDetailView(RecipeDetailViewModel recipeDetailViewModel,
                            RecipeDetailController recipeDetailController,
                            ServiceManager serviceManager) {
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.recipeDetailController = recipeDetailController;
        this.serviceManager = serviceManager;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final RecipeDetailState state = (RecipeDetailState) event.getNewValue();
        setFields(state);
    }

    private void setFields(RecipeDetailState state) {
    }

    @Override
    public String getViewName() {
        return view_name;
    }
}
