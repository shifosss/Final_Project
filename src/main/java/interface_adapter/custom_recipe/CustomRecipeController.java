package interface_adapter.custom_recipe;

import use_case.create_recipe.CustomRecipeInputBoundary;
import use_case.create_recipe.CustomRecipeInputData;

import java.util.List;

/**
 * Controller for the custom recipe view.
 */
public class CustomRecipeController {
    private final CustomRecipeInputBoundary customRecipeInteractor;

    public CustomRecipeController(CustomRecipeInputBoundary customRecipeInteractor) {
        this.customRecipeInteractor = customRecipeInteractor;
    }

    /**
     * Switches to the home view.
     */
    public void switchToHome() {
        customRecipeInteractor.switchToHomePage();
    }

    /**
     * Creates the custom recipe.
     * @param recipeName the name,
     * @param recipeInstruction the instruction.
     * @param ingredientNames the ingredient names.
     * @param ingredientMeasurements the ingredient measurements.
     * @param isAlcoholic depicts alcoholics to be expected.
     */
    public void createRecipe(String recipeName, String recipeInstruction,
                             List<String> ingredientNames, List<String> ingredientMeasurements,
                             String isAlcoholic) {
        final CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction,
                ingredientNames, ingredientMeasurements, isAlcoholic);
        customRecipeInteractor.saveCustomRecipe(inputData);
    }
}
