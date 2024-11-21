package use_case.login;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data for the login usecase.
 */
public class LoginOutputData {
    private String username;
    private List<Integer> ingredientsToAvoidId;
    private List<Recipe> randomRecipes;
    private boolean useCaseFailed;

    public LoginOutputData(String username, List<Integer> ingredientsToAvoidId, List<Recipe> randomRecipes, boolean useCaseFailed) {
        this.username = username;
        this.ingredientsToAvoidId = ingredientsToAvoidId;
        this.randomRecipes = randomRecipes;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public List<Recipe> getRandomRecipes() {
        return randomRecipes;
    }

    public List<Integer> getIngredientsToAvoidId() {
        return ingredientsToAvoidId;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
