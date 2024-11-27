package view.ui_components.recipe_detail;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;

/**
 * The panel showing if a recipe is alcoholic.
 */
public class IsAlcoholicPanel extends AbstractViewDecorator<RecipeDetailState> {
    private final JLabel messageLabel;

    public IsAlcoholicPanel(PageView<RecipeDetailState> view) {
        super(view);
        messageLabel = new JLabel();
        add(messageLabel);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);

        final Recipe recipe = state.getRecipe();

        messageLabel.setText(recipe.getIsAlcoholic());
    }
}
