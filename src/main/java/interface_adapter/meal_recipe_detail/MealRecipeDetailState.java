package interface_adapter.meal_recipe_detail;

import entities.recipe.MealRecipe;
import interface_adapter.recipe_detail.RecipeDetailState;

public class MealRecipeDetailState {
    private MealRecipe mealrecipe;
    private boolean isBookmarked;

    public MealRecipeDetailState(MealRecipeDetailState copy) {
        this.mealrecipe = copy.mealrecipe;
    }

    public MealRecipeDetailState() {
    }

    public MealRecipe getRecipe() {
        return mealrecipe;
    }

    public void setRecipe(MealRecipe recipe) {
        this.mealrecipe = recipe;
    }

    public void setIsBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }
}
