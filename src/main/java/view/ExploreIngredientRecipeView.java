package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.explore_ingredient.ExploreIngredientState;
import interface_adapter.services.ServiceManager;

/**
 * Explore ingredient recipe view.
 */
public class ExploreIngredientRecipeView extends JPanel implements PageView<ExploreIngredientState>,
        ActionListener, PropertyChangeListener {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final int GRID_COLUMNS = 3;

    private final String viewName = "explore ingredient";
    private final ExploreIngredientViewModel exploreViewModel;
    private final ExploreIngredientController exploreController;
    private final ServiceManager serviceManager;
    private final ViewManagerModel viewManagerModel;

    // UI Components
    private final JButton backButton;
    private final JPanel gridPanel;
    private final JPanel contentPanel;

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

        // Content panel for recipes
        contentPanel = new JPanel(new BorderLayout());

        // Grid panel for ingredients
        gridPanel = new JPanel(new GridLayout(0, GRID_COLUMNS, 10, 10));
        gridPanel.setBackground(BACKGROUND_COLOR);

        setupLayout();
        setupActionListeners();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        final JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        headerPanel.add(backButton, BorderLayout.WEST);

        final JLabel titleLabel = new JLabel("Explore by Ingredients");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        final JScrollPane ingredientScroll = new JScrollPane(gridPanel);
        ingredientScroll.setBorder(null);

        add(headerPanel, BorderLayout.NORTH);
        add(ingredientScroll, BorderLayout.CENTER);
    }

    private void setupActionListeners() {
        backButton.addActionListener(event -> {
            exploreController.switchToHome();
        });
    }

    private JButton createIngredientButton(String ingredient) {
        final JButton button = new JButton(ingredient);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        button.addActionListener(event -> exploreController.switchToRecipes(ingredient));

        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ExploreIngredientState state = (ExploreIngredientState) evt.getNewValue();

        // updates fields
        displayIngredients(state.getIngredients());
    }

    private void displayIngredients(List<String> ingredients) {
        gridPanel.removeAll();
        for (String ingredient : ingredients) {
            gridPanel.add(createIngredientButton(ingredient));
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

    @Override
    public void update(ExploreIngredientState state) {

    }
}