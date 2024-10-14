package usecase;

import api.RecipeDatabase;

/**
 * Gets top 10 popular meals.
 */
public class GetPopularMealsUseCase {
    private final RecipeDatabase mealsDatabase;

    public GetPopularMealsUseCase(RecipeDatabase mealsDatabase) {
        this.mealsDatabase = mealsDatabase;
    }
}
