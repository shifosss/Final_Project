package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.ui_components.search_recipe.RecipeScrollPanel;
import view.ui_components.search_recipe.SearchPanel;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search recipe";
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final SearchRecipeController searchRecipeController;
    private final ServiceManager serviceManager;
    private final ViewManagerModel viewManagerModel;

    // UI Components
    private final JTextField searchTextField;
    private final JButton searchButton;
    private final JButton backButton;
    private final JButton exploreIngredientsButton;
    private final RecipeScrollPanel recipeScrollPanel;

    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            ServiceManager serviceManager,
                            ViewManagerModel viewManagerModel) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.searchRecipeController = searchRecipeController;
        this.serviceManager = serviceManager;
        this.viewManagerModel = viewManagerModel;
        this.searchRecipeViewModel.addPropertyChangeListener(this);

        // Initialize components
        searchTextField = new JTextField(15);
        searchButton = new JButton("Search");
        backButton = new JButton("<");
        exploreIngredientsButton = new JButton("Explore by Ingredients");
        recipeScrollPanel = new RecipeScrollPanel(serviceManager);

        // Create search bar
        SearchPanel searchBar = new SearchPanel(
                backButton,
                searchTextField,
                searchButton,
                exploreIngredientsButton,
                recipeScrollPanel
        );

        // Set up main layout
        setLayout(new BorderLayout());
        add(searchBar, BorderLayout.NORTH);
        add(recipeScrollPanel, BorderLayout.CENTER);

        // Add action listeners
        setupActionListeners();
        setupSearchTextField();
    }

    private void setupActionListeners() {
        // Search button action
        searchButton.addActionListener(e -> {
            if (e.getSource().equals(searchButton)) {
                performSearchAction();
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            // Clear current state
            searchTextField.setText("");
            recipeScrollPanel.clearRecipes();

            // Navigate back to main menu
            viewManagerModel.setState("main menu");
            viewManagerModel.firePropertyChanged();
        });

        // Explore button action - Updated to navigate to explore ingredient view
        exploreIngredientsButton.addActionListener(e -> {
            // Navigate to explore ingredient view
            viewManagerModel.setState("explore ingredient");
            viewManagerModel.firePropertyChanged();
        });
    }

    private void setupSearchTextField() {
        // Enter key in search field
        searchTextField.addActionListener(e -> {
            if (e.getSource().equals(searchTextField)) {
                performSearchAction();
            }
        });

        // Text change listener
        searchTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void updateState() {
                SearchRecipeState currentState = searchRecipeViewModel.getState();
                currentState.setQuery(searchTextField.getText());
                searchRecipeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }
        });
    }

    private void performSearchAction() {
        SearchRecipeState currentState = searchRecipeViewModel.getState();
        searchRecipeController.execute(currentState.getQuery());
        recipeScrollPanel.displayRecipes(currentState.getRecipes());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchRecipeState state = (SearchRecipeState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchRecipeState state) {
        searchTextField.setText(state.getQuery());
    }

    public String getViewName() {
        return viewName;
    }
}