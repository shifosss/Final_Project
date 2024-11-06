package interface_adapter.search_recipe;

import java.util.List;

import domain.entities.recipe.Recipe;

/**
 * The state information representing the searched recipe.
 */
public class SearchRecipeState {
    private List<Recipe> recipes;
    private String query;

    public SearchRecipeState(SearchRecipeState copy) {
        this.recipes = copy.recipes;
        this.query = copy.query;
    }

    public SearchRecipeState() {
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
