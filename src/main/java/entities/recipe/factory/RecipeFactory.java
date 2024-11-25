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
     * @param imageLink the recipe image.
     * @param videoLink the recipe video.
     * @param isAlcoholic the alcoholic state of the recipe.
     * @return recipe entity.
     */
    Recipe create(String name, int id,
                  String instruction, List<Ingredient> ingredients,
                  String imageLink, String videoLink, String isAlcoholic);
}
