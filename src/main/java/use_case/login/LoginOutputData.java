package use_case.login;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data for the login usecase.
 */
public class LoginOutputData {
    private String username;
    private List<String> ingredientsToAvoidId;
    private List<Recipe> randomRecipes;
    private List<Recipe> bookmarkedRecipe;
    private List<String> ingredients;
    private boolean useCaseFailed;

    public LoginOutputData(String username, List<String> ingredientsToAvoidId,
                           List<Recipe> randomRecipes, List<Recipe> bookmarkedRecipe,
                           List<String> ingredients, boolean useCaseFailed) {
        this.username = username;
        this.ingredientsToAvoidId = ingredientsToAvoidId;
        this.randomRecipes = randomRecipes;
        this.bookmarkedRecipe = bookmarkedRecipe;
        this.ingredients = ingredients;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public List<Recipe> getRandomRecipes() {
        return randomRecipes;
    }

    public List<String> getIngredientsToAvoidId() {
        return ingredientsToAvoidId;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<Recipe> getBookmarkedRecipes() {
        return bookmarkedRecipe;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
