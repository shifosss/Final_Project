package view;

import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.services.ServiceManager;
import view.concrete_page.RecipeDetailConcrete;
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
public class RecipeDetailView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String view_name = "recipe detail";

    private final JButton backButton = new JButton("<");
    private final JButton bookmarkButton = new JButton("Bookmark!");

    private final PageView<RecipeDetailState> pageHandler;

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

        final RecipeDetailConcrete recipeDetailConcrete = new RecipeDetailConcrete();
        final VideoPanel videoPanel = new VideoPanel(recipeDetailConcrete, serviceManager);
        final IsAlcoholicPanel alcoholicPanel = new IsAlcoholicPanel(videoPanel);
        final InstructionPanel instructionPanel = new InstructionPanel(alcoholicPanel);
        final IngredientPanel ingredientPanel = new IngredientPanel(instructionPanel);
        final RecipeTitlePanel recipeTitlePanel = new RecipeTitlePanel(ingredientPanel);
        final NavigationActionPanel navigationActionPanel = new NavigationActionPanel(
                recipeTitlePanel, backButton, bookmarkButton
        );

        pageHandler = navigationActionPanel;

        final ActionListener switchToSearchListener = event -> {
            if (event.getSource().equals(backButton)) {
                recipeDetailController.switchToSearchView();
            }
        };

        final ActionListener bookMarkListener = event -> {
            if (event.getSource().equals(bookmarkButton)) {
                final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
                recipeDetailController.bookmarkRecipe(
                        recipeDetailState.getRecipe().getId());
            }
        };

        backButton.addActionListener(switchToSearchListener);
        bookmarkButton.addActionListener(bookMarkListener);

        // Set main layout
        setLayout(new BorderLayout());

        // Top section
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(navigationActionPanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        // Center section
        final JPanel centerPanel = new JPanel();
        final JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(recipeTitlePanel);
        centerPanel.add(videoPanel);
        centerPanel.add(ingredientPanel);
        centerPanel.add(instructionPanel);
        centerPanel.add(alcoholicPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final RecipeDetailState state = (RecipeDetailState) event.getNewValue();
        if (event.getPropertyName().equals("state")) {
            pageHandler.update(state);
        }
        else if (event.getPropertyName().equals("bookmark")) {
            String message = "bookmarked";
            if (!state.getIsBookmarked()) {
                message = "un-" + message;
            }
            JOptionPane.showMessageDialog(null,
                    String.format("Recipe: %s successfully %s",
                            state.getRecipe().getName(), message));
        }
    }

    public String getViewName() {
        return view_name;
    }
}
