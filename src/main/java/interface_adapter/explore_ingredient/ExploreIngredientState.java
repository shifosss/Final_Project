package interface_adapter.explore_ingredient;

import java.util.List;

import entities.recipe.SimpleCocktailRecipe;
import entities.recipe.Ingredient;

public class ExploreIngredientState {
    private List<SimpleCocktailRecipe> recipes;
    private List<Ingredient> ingredients;
    private String query;

    public ExploreIngredientState(ExploreIngredientState copy) {
        this.recipes = copy.recipes;
        this.query = copy.query;
        this.ingredients = copy.ingredients;
    }

    public ExploreIngredientState() {}

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setRecipes(List<SimpleCocktailRecipe> recipes) {
        this.recipes = recipes;
    }

    public List<SimpleCocktailRecipe> getRecipes() {
        return recipes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}