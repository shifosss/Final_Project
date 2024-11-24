package use_case.create_recipe;

import entities.recipe.Recipe;

public interface CustomRecipeInputBoundary{
    /**
     * Creates a new custom recipe.
     *
     * @param name        The name of the recipe.
     * @param ingredients The ingredients and measurements of the recipe.
     * @param isAlcoholic The alcoholic status of the recipe.
     * @return The created recipe object.
     */
    Recipe customRecipe(String name, String[][] ingredients, String isAlcoholic);
}