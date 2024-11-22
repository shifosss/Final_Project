package entities.recipe;

/**
 * A simplified version of Recipe entity that only contains
 * essential information for the explore ingredients feature.
 */
public class SimpleCocktailRecipe extends Recipe{
    private final String name;
    private final int id;
    private final String imageLink;

    public SimpleCocktailRecipe(String name, int id, String imageLink) {
        this.name = name;
        this.id = id;
        this.imageLink = imageLink;
    }

    /**
     * returns the getName of the recipe.
     *
     * @return getName of recipe.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the getId of the recipe.
     *
     * @return getId of recipe.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * String representation of recipe.
     *
     * @return string repr of recipe.
     */
    @Override
    public String toString() {
        return String.format("Name: %s", name);
    }

    /**
     * Image link of the recipe.
     *
     * @return link of recipe image.
     */
    @Override
    public String getImageLink() {
        return imageLink;
    }
}