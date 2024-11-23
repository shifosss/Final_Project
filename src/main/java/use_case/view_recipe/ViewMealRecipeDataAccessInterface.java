package use_case.view_recipe;

import entities.recipe.Recipe;

public interface ViewMealRecipeDataAccessInterface {

    Recipe getRecipeById(String id);

}

