package entities.recipe.factory;

import java.util.List;

import entities.recipe.CocktailRecipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Creates cocktail recipes.
 */
public class CocktailFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, int id,
                         String instruction, List<Ingredient> ingredients,
                         String imageLink, String videoLink) {
        return new CocktailRecipe(name, id, instruction, ingredients, imageLink, videoLink);
    }

    @Override
    public Recipe create_meals(String name, String id, String instruction, List<Ingredient> ingredients, String imageLink, String videoLink) {
        return null;
    }
}
