package domain.use_case.search_recipes;

import java.util.List;

import domain.entities.recipe.Recipe;

/**
 * Output Data for the Search Recipe Use Case.
 */
public class SearchRecipeOutputData {
    private List<Recipe> recipes;
    private final boolean useCaseFailed;

    public SearchRecipeOutputData(List<Recipe> recipes, boolean useCaseFailed) {
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
