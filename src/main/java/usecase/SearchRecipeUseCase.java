package usecase;

import api.RecipeDatabase;

/**
 * Search Recipes at both meals and cocktails database.
 */
public class SearchRecipeUseCase {
    private final RecipeDatabase mealsDatabase;
    private final RecipeDatabase cocktailsDatabase;

    public SearchRecipeUseCase(RecipeDatabase mealsDatabase, RecipeDatabase cocktailsDatabase) {
        this.mealsDatabase = mealsDatabase;
        this.cocktailsDatabase = cocktailsDatabase;
    }

}
