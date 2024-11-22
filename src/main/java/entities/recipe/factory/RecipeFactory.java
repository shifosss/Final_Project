package entities.recipe.factory;

import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Recipe Factory interface.
 */
public interface RecipeFactory {

    /**
     * Creates a recipe.
     * @param name recipe getName.
     * @param id recipe getId.
     * @param instruction recipe instruction.
     * @param ingredients recipe ingredients.
     * @return recipe entity.
     */
    Recipe createDetailRecipe(String name, int id,
                              String instruction, List<Ingredient> ingredients,
                              String imageLink, String videoLink);

    Recipe createSimpleRecipe(String name, int id,
                              String imageLink);
}
