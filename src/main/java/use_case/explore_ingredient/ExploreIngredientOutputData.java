package use_case.explore_ingredient;

import java.util.List;
import entities.recipe.Recipe;
import entities.recipe.Ingredient;
import entities.recipe.SimpleRecipe;

public class ExploreIngredientOutputData {
    private final List<?> data;  // Can be either List<Recipe> or List<Ingredient>
    private final boolean useCaseFailed;
    private final boolean isIngredientList;

    public ExploreIngredientOutputData(List<?> data, boolean useCaseFailed) {
        this.data = data;
        this.useCaseFailed = useCaseFailed;
        this.isIngredientList = !data.isEmpty() && data.get(0) instanceof Ingredient;
    }

    @SuppressWarnings("unchecked")
    public List<Recipe> getRecipes() {
        return isIngredientList ? null : (List<Recipe>) data;
    }

    @SuppressWarnings("unchecked")
    public List<SimpleRecipe> getSimpleRecipes() {
        return isIngredientList? (List<SimpleRecipe>) data : null;
    }

    @SuppressWarnings("unchecked")
    public List<Ingredient> getIngredients() {
        return isIngredientList ? (List<Ingredient>) data : null;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public boolean isIngredientList() {
        return isIngredientList;
    }
}