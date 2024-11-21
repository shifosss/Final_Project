package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.explore_ingredient.ExploreIngredientState;
import interface_adapter.services.ServiceManager;
import view.ui_components.explore_ingredient.RecipeScrollPanel;

public class ExploreIngredientRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "explore ingredient";  // Matches ViewModel's view name
    private final ExploreIngredientViewModel exploreViewModel;
    private final ExploreIngredientController exploreController;
    private final ServiceManager serviceManager;
    private final ViewManagerModel viewManagerModel;

    // UI Components
    private final JButton backButton;
    private final RecipeScrollPanel recipeScrollPanel;
    private final JPanel gridPanel;
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final int GRID_COLUMNS = 3;

    public ExploreIngredientRecipeView(
            ExploreIngredientViewModel exploreViewModel,
            ExploreIngredientController exploreController,
            ServiceManager serviceManager,
            ViewManagerModel viewManagerModel) {

        this.exploreViewModel = exploreViewModel;
        this.exploreController = exploreController;
        this.serviceManager = serviceManager;
        this.viewManagerModel = viewManagerModel;
        this.exploreViewModel.addPropertyChangeListener(this);

        // Initialize components
        backButton = new JButton("<");
        recipeScrollPanel = new RecipeScrollPanel(serviceManager);

        // Set up main layout
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        // Create header panel with back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Explore by Ingredients");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        headerPanel.add(backButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Create grid panel for ingredients
        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, 10, 10));
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create scrollable panel for ingredients
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Add components to main panel
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Setup action listeners
        setupActionListeners();
    }

    private void setupActionListeners() {
        backButton.addActionListener(e -> {
            // Navigate back to main menu
            viewManagerModel.setState("main menu");
            viewManagerModel.firePropertyChanged();
        });
    }

    private void displayIngredients(List<Ingredient> ingredients) {
        gridPanel.removeAll();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.getName().isEmpty()) {
                JButton ingredientButton = createIngredientButton(ingredient);
                gridPanel.add(ingredientButton);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private JButton createIngredientButton(Ingredient ingredient) {
        JButton button = new JButton(ingredient.getName());
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        button.addActionListener(e -> {
            // Use the existing execute method from the controller
            exploreController.execute(ingredient.getName());
        });

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(245, 245, 245));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 255, 255));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExploreIngredientState state = (ExploreIngredientState) evt.getNewValue();

        if (state.getIngredients() != null) {
            displayIngredients(state.getIngredients());
        }

        if (state.getRecipes() != null) {
            recipeScrollPanel.displayRecipes(state.getRecipes());
            removeAll();
            setLayout(new BorderLayout());

            // Add back button at the top
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(BACKGROUND_COLOR);
            headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            headerPanel.add(backButton, BorderLayout.WEST);

            add(headerPanel, BorderLayout.NORTH);
            add(recipeScrollPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
        }
    }

    public String getViewName() {
        return viewName;
    }
}