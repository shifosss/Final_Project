package domain.entities.recipe.factory;

import java.util.List;

import domain.entities.recipe.CocktailRecipe;
import domain.entities.recipe.Ingredient;
import domain.entities.recipe.Recipe;

/**
 * Creates cocktail recipes.
 */
public class MealFactory implements RecipeFactory {

    @Override
    public Recipe create(String name, int id,
                         String instruction, List<Ingredient> ingredients,
                         String imageLink) {
        return new CocktailRecipe(name, id, instruction, ingredients, imageLink);
    }
}
