package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Ingredient;

/**
 * Output Data for the Explore Ingredient Use Case.
 */
public class ExploreIngredientOutputData {
    private List<Ingredient> ingredients;
    private final boolean useCaseFailed;

    public ExploreIngredientOutputData(List<Ingredient> ingredients, boolean useCaseFailed) {
        this.ingredients = ingredients;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
