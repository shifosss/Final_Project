package domain.entities.recipe;

import java.util.List;

/**
 * Cocktails Recipe.
 */
public class CocktailRecipe implements Recipe {
    private final String name;
    private final int id;
    private final String instruction;
    private final List<Ingredient> ingredients;
    private final String imageLink;

    public CocktailRecipe(String name, int id,
                          String instruction, List<Ingredient> ingredients,
                          String imageLink) {
        this.name = name;
        this.id = id;
        this.instruction = instruction;
        this.ingredients = ingredients;
        this.imageLink = imageLink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getInstruction() {
        return instruction;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nIngredients: %s", name, ingredients);
    }

    @Override
    public String getImageLink() {
        return imageLink;
    }
}
