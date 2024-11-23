package use_case.create_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data for the custom recipe usecase.
 */
public class CustomRecipeOutputData {
    private final List<Recipe> bookmarkedRecipes;
    private final List<Recipe> randomRecipes;

    public CustomRecipeOutputData(List<Recipe> bookmarkedRecipes, List<Recipe> randomRecipes) {
        this.bookmarkedRecipes = bookmarkedRecipes;
        this.randomRecipes = randomRecipes;
    }

    public List<Recipe> getBookmarkedRecipes() {
        return bookmarkedRecipes;
    }

    public List<Recipe> getRandomRecipes() {
        return randomRecipes;
    }
}
