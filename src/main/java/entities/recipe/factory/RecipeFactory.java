package entities.recipe.factory;

import java.util.List;

import entities.recipe.CocktailRecipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Recipe Factory interface.
 */
public class RecipeFactory {

    /**
     * Creates a recipe.
     * @param name recipe name.
     * @param id recipe id.
     * @param instruction recipe instruction.
     * @param ingredients recipe ingredients.
     * @param imageLink the recipe image.
     * @param videoLink the recipe video.
     * @param isAlcoholic the alcoholic state of the recipe.
     * @param type the type of recipe.
     * @return recipe entity.
     * @throws IllegalArgumentException if the type is unrecognized.
     */
    public Recipe create(String name, int id,
                  String instruction, List<Ingredient> ingredients,
                  String imageLink, String videoLink, String isAlcoholic, String type) {
        switch (type) {
            case "cocktail":
                return new CocktailRecipe(name, id, instruction, ingredients, imageLink, videoLink, isAlcoholic);
            default:
                throw new IllegalArgumentException("Invalid type of recipe");
        }
    }
}
