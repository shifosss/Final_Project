package entities.recipe.factory;

import java.util.List;

import entities.recipe.CocktailRecipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;
import entities.recipe.SimpleCocktailRecipe;

/**
 * Creates cocktail recipes.
 */
public class CocktailFactory implements RecipeFactory {

    @Override
    public CocktailRecipe createDetailRecipe(String name, int id,
                                             String instruction, List<Ingredient> ingredients,
                                             String imageLink, String videoLink) {
        return new CocktailRecipe(name, id, instruction, ingredients, imageLink, videoLink);
    }

    @Override
    public Recipe createSimpleRecipe(String name, int id,
                                     String imageLink) {
        return new SimpleCocktailRecipe(name, id, imageLink);
    }
}
