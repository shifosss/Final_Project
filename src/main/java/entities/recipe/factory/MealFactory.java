package entities.recipe.factory;

import java.util.List;

import entities.recipe.MealRecipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Creates cocktail recipes.
 */
public class MealFactory implements RecipeFactory {

    public Recipe create(String name, String id,
                         String instruction, List<Ingredient> ingredients,
                         String imageLink, String videoLink) {
        return new MealRecipe(name, id, instruction, ingredients, imageLink, videoLink);
    }

    @Override
    public Recipe create(String name, int id, String instruction, List<Ingredient> ingredients, String imageLink, String videoLink) {
        return null;
    }
}
