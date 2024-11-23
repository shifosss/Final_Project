package interface_adapter.meal_recipe_detail;

import entities.recipe.MealRecipe;
import use_case.view_recipe.ViewMealRecipeInputBoundary;
import use_case.view_recipe.ViewMealRecipeInputData;
import use_case.view_recipe.ViewRecipeInputData;

public class MealRecipeDetailController {
    private final ViewMealRecipeInputBoundary mealrecipeDetailInteractor;

    public MealRecipeDetailController(ViewMealRecipeInputBoundary recipeDetailInteractor) {
        this.mealrecipeDetailInteractor = recipeDetailInteractor;
    }

    /**
     * Executes the interactor for recipe detail use case.
     * @param id recipe id.
     */
    public void execute(String id) {
        final ViewMealRecipeInputData mealrecipeDetailInputData = new ViewMealRecipeInputData(id);
        mealrecipeDetailInteractor.execute(mealrecipeDetailInputData);
    }

    public void switchToRecipe(String id) {
        final ViewMealRecipeInputData MealrecipeDetailInputData = new ViewMealRecipeInputData(id);

    }

    /**
     * Attempts to bookmark the recipe.
     * @param recipe the recipe entity.
     */
    public void bookmarkRecipe(MealRecipe recipe) {
        final ViewMealRecipeInputData mealrecipeDetailInputData = new ViewMealRecipeInputData(recipe.getMealId());
        mealrecipeDetailInteractor.bookmarkRecipe(mealrecipeDetailInputData);
    }
}
