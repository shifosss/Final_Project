package usecase;

import api.RecipeDatabase;

/**
 * Gets all available areas in the meals database.
 */
public class GetAllAreasUseCase {
    private final RecipeDatabase mealsDatabase;

    public GetAllAreasUseCase(RecipeDatabase mealsDatabase) {
        this.mealsDatabase = mealsDatabase;
    }
}
