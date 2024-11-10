package view;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.services.ServiceManager;
import view.ui_components.recipe_detail.*;

import javax.swing.*;
import java.awt.*;
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

    private final JButton backButton = new JButton("<");
    private final JButton bookmarkButton = new JButton("Bookmark!");

    private final JScrollPane scrollPane;
    private final RecipeTitlePanel recipeTitlePanel;
    private final IngredientPanel ingredientPanel;
    private final InstructionPanel instructionPanel;
    private final VideoPanel videoPanel;

    private final RecipeDetailViewModel recipeDetailViewModel;
    private final ServiceManager serviceManager;
    private final RecipeDetailController recipeDetailController;

    public RecipeDetailView(RecipeDetailViewModel recipeDetailViewModel,
                            RecipeDetailController recipeDetailController,
                            ServiceManager serviceManager) {
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.recipeDetailController = recipeDetailController;
        this.serviceManager = serviceManager;

        this.recipeDetailViewModel.addPropertyChangeListener(this);

        final NavigationActionPanel navigationActionPanel = new NavigationActionPanel(
                backButton, bookmarkButton
        );
        recipeTitlePanel = new RecipeTitlePanel(
        );
        videoPanel = new VideoPanel(
                serviceManager
        );

        ingredientPanel = new IngredientPanel(
        );

        instructionPanel = new InstructionPanel(
        );

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipeDetailController.switchToSearchView();
            }
        });

        // Set main layout
        setLayout(new BorderLayout());

        // Top section
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(navigationActionPanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        // Center section
        final JPanel centerPanel = new JPanel();
        scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(recipeTitlePanel);
        centerPanel.add(videoPanel);
        centerPanel.add(ingredientPanel);
        centerPanel.add(instructionPanel);
        add(scrollPane, BorderLayout.CENTER);
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
        // Updates the Recipe detail view.
        final Recipe recipe = state.getRecipe();
        // sets the recipe title
        recipeTitlePanel.updateComponents(recipe);
        videoPanel.updateComponents(recipe);
        ingredientPanel.updateComponents(recipe);
        instructionPanel.updateComponents(recipe);
        // TODO: Update the scroll panel so that the scroll bar is always at the top
        //  (so the viewing always starts at the top)
    }

    @Override
    public String getViewName() {
        return view_name;
    }
}
