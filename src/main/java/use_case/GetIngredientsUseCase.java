package use_case;

import api.RecipeDatabase;

/**
 * Gets all ingredients present at both meals and cocktails database.
 */
public class GetIngredientsUseCase {
    private final RecipeDatabase mealsDatabase;
    private final RecipeDatabase cocktailsDatabase;

    public GetIngredientsUseCase(RecipeDatabase mealsDatabase, RecipeDatabase cocktailsDatabase) {
        this.mealsDatabase = mealsDatabase;
        this.cocktailsDatabase = cocktailsDatabase;
    }
}
