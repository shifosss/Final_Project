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
import view.ui_components.explore_ingredient.SimpleRecipePanel;
import view.ui_components.explore_ingredient.SimpleRecipeScrollPanel;

// COMMENT OUT FOR DETAILED VIEW ONLY: import view.ui_components.explore_ingredient.SimpleRecipeScrollPanel;
// UNCOMMENT FOR DETAILED VIEW: import view.ui_components.explore_ingredient.RecipeScrollPanel;

public class ExploreIngredientRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "explore ingredient";
    private final ExploreIngredientViewModel exploreViewModel;
    private final ExploreIngredientController exploreController;
    private final ServiceManager serviceManager;
    private final ViewManagerModel viewManagerModel;
    private SimpleRecipeScrollPanel simpleRecipePanel;

    // UI Components
    private final JButton backButton;
    // COMMENT OUT FOR DETAILED VIEW ONLY: private final SimpleRecipeScrollPanel simpleRecipePanel;
    // UNCOMMENT FOR DETAILED VIEW: private final RecipeScrollPanel detailRecipePanel;
    private final JPanel gridPanel;
    private final JPanel contentPanel;

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

        // COMMENT OUT FOR DETAILED VIEW ONLY:
        simpleRecipePanel = new SimpleRecipeScrollPanel(serviceManager);

        // UNCOMMENT FOR DETAILED VIEW:
        /*
        detailRecipePanel = new RecipeScrollPanel(serviceManager);
        */

        // Content panel for recipes
        contentPanel = new JPanel(new BorderLayout());

        // COMMENT OUT FOR DETAILED VIEW ONLY:
        contentPanel.add(simpleRecipePanel, BorderLayout.CENTER);

        // UNCOMMENT FOR DETAILED VIEW:
        /*
        contentPanel.add(detailRecipePanel, BorderLayout.CENTER);
        */

        // Grid panel for ingredients
        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, 10, 10));
        gridPanel.setBackground(BACKGROUND_COLOR);

        setupLayout();
        setupActionListeners();
        exploreController.loadIngredients();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Explore by Ingredients");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JScrollPane ingredientScroll = new JScrollPane(gridPanel);
        ingredientScroll.setBorder(null);

        add(headerPanel, BorderLayout.NORTH);
        add(ingredientScroll, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
        backButton.addActionListener(e -> {
            viewManagerModel.setState("main menu");
            viewManagerModel.firePropertyChanged();
        });
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
            removeAll();
            setLayout(new BorderLayout());

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(BACKGROUND_COLOR);
            headerPanel.add(backButton, BorderLayout.WEST);

            add(headerPanel, BorderLayout.NORTH);
            add(contentPanel, BorderLayout.CENTER);

            revalidate();
            repaint();

            exploreController.execute(ingredient.getName());
        });

        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExploreIngredientState state = (ExploreIngredientState) evt.getNewValue();

        if (state.getIngredients() != null) {
            displayIngredients(state.getIngredients());
        }

        if (state.getRecipes() != null) {
            // COMMENT OUT FOR DETAILED VIEW ONLY:
            simpleRecipePanel.displayRecipes(state.getRecipes());

            // UNCOMMENT FOR DETAILED VIEW:
            /*
            detailRecipePanel.displayRecipes(state.getRecipes());
            */
        }
    }

    private void displayIngredients(List<Ingredient> ingredients) {
        gridPanel.removeAll();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.getName().isEmpty()) {
                gridPanel.add(createIngredientButton(ingredient));
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }
}