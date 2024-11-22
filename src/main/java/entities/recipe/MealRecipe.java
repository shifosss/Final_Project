package entities.recipe;

import java.util.List;

/**
 * Meals Recipe.
 */
public class MealRecipe extends Recipe {
    private final String name;
    private final int id;
    private final String instruction;
    private final List<Ingredient> ingredients;
    private final String imageLink;
    private final String videoLink;

    public MealRecipe(String name, int id,
                      String instruction, List<Ingredient> ingredients,
                      String imageLink, String videoLink) {
        this.name = name;
        this.id = id;
        this.instruction = instruction;
        this.ingredients = ingredients;
        this.imageLink = imageLink;
        this.videoLink = videoLink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }

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

    public String getVideoLink() {
        return videoLink;
    }

}
