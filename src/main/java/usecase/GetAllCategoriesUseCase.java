package usecase;

import api.RecipeDatabase;

/**
 * Gets all available categories in both meals and cocktails database.
 */
public class GetAllCategoriesUseCase {
    private final RecipeDatabase mealsDatabase;
    private final RecipeDatabase cocktailsDatabase;

    public GetAllCategoriesUseCase(RecipeDatabase mealsDatabase, RecipeDatabase cocktailsDatabase) {
        this.mealsDatabase = mealsDatabase;
        this.cocktailsDatabase = cocktailsDatabase;
    }

    /**
     * Returns the categories in both databases.
     * @return array of category string.
     */
    public String[] getCategories() {
        return cocktailsDatabase.getCategories();
    }
}
