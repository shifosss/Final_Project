package entities.recipe.factory;

import java.util.List;

import entities.recipe.CocktailRecipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Creates cocktail recipes.
 */
public class MealFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, int id,
                         String instruction, List<Ingredient> ingredients,
                         String imageLink, String videoLink, String isAlcoholic) {
        return new CocktailRecipe(name, id, instruction, ingredients, imageLink, videoLink, isAlcoholic);
    }
}
