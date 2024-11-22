package entities.recipe;

import java.util.List;

/**
 * Meals Recipe.
 */
public class MealRecipe implements Recipe {
    private final String name;
    private final String id;
    private final String instruction;
    private final List<Ingredient> ingredients;
    private final String imageLink;
    private final String videoLink;

    public MealRecipe(String name, String id,
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
        return 0;
    }

    @Override
    public String getMealId() {
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

    @Override
    public String getVideoLink() {
        return videoLink;
    }

}
