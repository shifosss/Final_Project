package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.ui_components.search_recipe.ThumbnailsContainerPanel;
import view.ui_components.search_recipe.SearchHeaderPanel;

/**
 * The view when the user searches for a recipe through some text field.
 */
public class SearchRecipeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final String viewName = "search recipe";

    private final JTextField searchTextField = new JTextField(15);

    private final JButton searchButton;
    private final JButton backButton;

    private final ThumbnailsContainerPanel thumbnailContainerPanel;

    private final SearchRecipeViewModel searchRecipeViewModel;
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
        thumbnailContainerPanel = new ThumbnailsContainerPanel(
                searchRecipeViewModel, searchRecipeController,
                serviceManager);

        // Create SearchPanel with all four parameters
        final SearchHeaderPanel searchBar = new SearchHeaderPanel(
                backButton, searchTextField, searchButton
        );

        final ActionListener switchToHomeViewListener = event -> {
            if (event.getSource().equals(backButton)) {
                final SearchRecipeState state = searchRecipeViewModel.getState();
                searchRecipeController.switchToHomeView(state.getQuery());
            }
        };

        final ActionListener searchRecipeListener = event -> {
            if (event.getSource().equals(searchButton) || event.getSource().equals(searchTextField)) {
                final SearchRecipeState currentState = searchRecipeViewModel.getState();
                searchRecipeController.execute(currentState.getQuery(), currentState.getRecipes());
            }
        };

        backButton.addActionListener(switchToHomeViewListener);
        searchButton.addActionListener(searchRecipeListener);

        setLayout(new BorderLayout());
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        searchTextField.addActionListener(searchRecipeListener);
        addSearchTextFieldListener();

        this.add(searchBar, BorderLayout.NORTH);
        this.add(thumbnailContainerPanel, BorderLayout.CENTER);
    }

    private void addSearchTextFieldListener() {
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
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // We pop up a JOptionPane to the user.
        final SearchRecipeState state = (SearchRecipeState) evt.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    private void setFields(SearchRecipeState state) {
        thumbnailContainerPanel.displayRecipes(state.getRecipes());
    }
}
