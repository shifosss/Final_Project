package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.ui_components.search_recipe.RecipeScrollPanel;
import view.ui_components.search_recipe.SearchPanel;

/**
 * The view when the user searches for a recipe through some text field.
 */
public class SearchRecipeView extends JPanel implements
        PageView, ActionListener, PropertyChangeListener {
    private final String viewName = "search recipe";
    private final SearchRecipeViewModel searchRecipeViewModel;

    private final JTextField searchTextField = new JTextField(15);

    private final JButton searchButton;
    private final JButton backButton;

    private final RecipeScrollPanel recipeScrollPanel;
    private final ServiceManager serviceManager;
    private final SearchRecipeController searchRecipeController;

    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            ServiceManager serviceManager) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.searchRecipeController = searchRecipeController;
        this.serviceManager = serviceManager;

        this.searchRecipeViewModel.addPropertyChangeListener(this);

        searchButton = new JButton("Search");
        backButton = new JButton("<");

        // Create RecipeScrollPanel firsts
        recipeScrollPanel = new RecipeScrollPanel(this.serviceManager);

        // Create SearchPanel with all four parameters
        final SearchPanel searchBar = new SearchPanel(
                backButton, searchTextField, searchButton, recipeScrollPanel
        );

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource().equals(searchButton)) {
                    performSearchAction();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource().equals(searchTextField)) {
                    performSearchAction();
                }
            }
        });

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SearchRecipeState currentState = searchRecipeViewModel.getState();
                currentState.setQuery(searchTextField.getText());
                searchRecipeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(searchBar);
        this.add(recipeScrollPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: Make it so that when the user query does not match any recipe
        // We pop up a JOptionPane to the user.
        final SearchRecipeState state = (SearchRecipeState) evt.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    private void performSearchAction() {
        final SearchRecipeState currentState = searchRecipeViewModel.getState();
        searchRecipeController.execute(currentState.getQuery());
        // searchRecipeController.displayRecipes(currentState.getRecipes());
        recipeScrollPanel.displayRecipes(currentState.getRecipes());
    }

    private void setFields(SearchRecipeState state) {
        // searchTextField.setText(state.getQuery());
    }
}
