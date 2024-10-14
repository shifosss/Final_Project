package usecase;

import api.RecipeDatabase;

/**
 * Gets top 10 popular drinks.
 */
public class GetPopularCocktailsUseCase {
    private final RecipeDatabase cocktailsDatabase;

    public GetPopularCocktailsUseCase(RecipeDatabase cocktailsDatabase) {
        this.cocktailsDatabase = cocktailsDatabase;
    }
}
