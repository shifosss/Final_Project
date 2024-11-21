package interface_adapter.explore_ingredient;

import java.util.List;

import entities.recipe.Recipe;
import entities.recipe.Ingredient;

/**
 * The state information representing the searched recipe.
 */
public class ExploreIngredientState {
    private List<Recipe> recipes;
    private List<Ingredient> ingredients;
    private String query;

    public ExploreIngredientState(ExploreIngredientState copy) {
        this.recipes = copy.recipes;
        this.query = copy.query;
    }

    public ExploreIngredientState() {
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
