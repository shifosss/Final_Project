package interface_adapter.custom_recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * State of the custom recipe view.
 */
public class CustomRecipeState {
    private String recipeName = "";
    private String recipeInstruction = "";
    private List<String> ingredients = new ArrayList<>();
    private List<String> measurements = new ArrayList<>();

    public CustomRecipeState(CustomRecipeState copy) {
        this.recipeName = copy.recipeName;
        this.recipeInstruction = copy.recipeInstruction;
        this.ingredients = copy.ingredients;
        this.measurements = copy.measurements;
    }

    public CustomRecipeState() {
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(String recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<String> measurements) {
        this.measurements = measurements;
    }
}
