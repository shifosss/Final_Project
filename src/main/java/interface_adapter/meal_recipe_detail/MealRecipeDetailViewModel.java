package interface_adapter.meal_recipe_detail;

import interface_adapter.ViewModel;
import interface_adapter.recipe_detail.RecipeDetailState;

public class MealRecipeDetailViewModel extends ViewModel<MealRecipeDetailState> {
    public MealRecipeDetailViewModel() {
        super("meal recipe detail");
        setState(new MealRecipeDetailState());
    }
}
