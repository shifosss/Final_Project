package view.concrete_page;

import interface_adapter.recipe_detail.RecipeDetailState;
import view.PageView;

/**
 * Recipe Detail View Concrete PageView.
 */
public class RecipeDetailConcrete implements PageView<RecipeDetailState> {

    @Override
    public void update(RecipeDetailState state) {
        System.out.println("Recipe detail view updated.");
    }
}
