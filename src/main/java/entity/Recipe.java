package entity;

/**
 * Recipe that holds information about recipes? :sob:.
 */
public class Recipe {
    private final Info info;
    private final Ingredients ingredients;

    public Recipe(Info info, Ingredients ingredients) {
        this.info = info;
        this.ingredients = ingredients;
    }

    public Info getInfo() {
        return info;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }
}
