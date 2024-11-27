package view.ui_components.recipe_detail;

import javax.swing.*;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

import java.awt.*;

/**
 * Contains the title of the recipe.
 */
public class RecipeTitlePanel extends AbstractViewDecorator<RecipeDetailState> {
    private JLabel titleLabel;

    public RecipeTitlePanel(PageView<RecipeDetailState> view) {
        super(view);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        titleLabel = new JLabel("Recipe Title", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel, BorderLayout.WEST);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);
        final Recipe recipe = state.getRecipe();
        titleLabel.setText(recipe.getName() + " (ID: " + recipe.getId() + ")");
    }
}
