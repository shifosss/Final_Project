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
     * @param name recipe name.
     * @param id recipe id.
     * @param instruction recipe instruction.
     * @param ingredients recipe ingredients.
     * @return recipe entity.
     */
    Recipe create(String name, int id,
                  String instruction, List<Ingredient> ingredients,
                  String imageLink, String videoLink);

    Recipe create_meals(String name, String id, String instruction, List<Ingredient> ingredients,
                        String imageLink, String videoLink);
}
