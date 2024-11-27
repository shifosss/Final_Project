package use_case.create_recipe;

import java.util.List;

/**
 * Input data for the custom recipe usecase.
 */
public class CustomRecipeInputData {
    private final String recipeName;
    private final String recipeInstruction;
    private final List<String> ingredientNames;
    private final List<String> ingredientMeasurements;
    private final String isAlcoholic;

    public CustomRecipeInputData(String recipeName, String recipeInstruction,
                                 List<String> ingredientNames, List<String> ingredientMeasurements,
                                 String isAlcoholic) {
        this.recipeName = recipeName;
        this.recipeInstruction = recipeInstruction;
        this.ingredientNames = ingredientNames;
        this.ingredientMeasurements = ingredientMeasurements;
        this.isAlcoholic = isAlcoholic;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeInstruction() {
        return recipeInstruction;
    }

    public List<String> getIngredients() {
        return ingredientNames;
    }

    public List<String> getMeasurements() {
        return ingredientMeasurements;
    }

    public String getIsAlcoholic() {
        return isAlcoholic;
    }
}
