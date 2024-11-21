package use_case.search_recipes;

import java.util.List;

import entities.recipe.Recipe;

/**
 * Output Data for the Search Recipe Use Case.
 */
public class SearchRecipeOutputData {
    private final List<Recipe> recipes;
    private final String query;
    private final boolean useCaseFailed;

    public SearchRecipeOutputData(String query, List<Recipe> recipes, boolean useCaseFailed) {
        this.query = query;
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getQuery() {
        return query;
    }
}
