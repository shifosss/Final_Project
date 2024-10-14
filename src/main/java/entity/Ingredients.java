package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Ingredients Class that holds ingredients about a recipe.
 */
public class Ingredients {
    private final Map<String, String> ingredientList = new HashMap<>();

    public Ingredients(String[] ingredients, String[] measurements) {
        populateIngredients(ingredients, measurements);
    }

    private void populateIngredients(String[] ingredients, String[] measurements) {
        // assume that ingredients and measurements has equal length/size
        for (int i = 0; i < ingredients.length; i++) {
            ingredientList.put(ingredients[i], measurements[i]);
        }
    }

    public Map<String, String> getIngredientList() {
        return new HashMap<>(ingredientList);
    }
}
