package app;

import api.CocktailsDatabase;
import api.MealsDatabase;
import api.RecipeDatabase;
import usecase.GetAllCategoriesUseCase;

/**
 * Configures the application so that it has everything it needs.
 */
public class Config {
    private final RecipeDatabase mealsDatabase = new MealsDatabase();
    private final RecipeDatabase cocktailsDatabase = new CocktailsDatabase();

    public GetAllCategoriesUseCase getAllCategoriesUseCase() {
        return new GetAllCategoriesUseCase(mealsDatabase, cocktailsDatabase);
    }
}
