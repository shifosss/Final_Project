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
                         String imageLink) {
        return new CocktailRecipe(name, id, instruction, ingredients, imageLink);
    }
}