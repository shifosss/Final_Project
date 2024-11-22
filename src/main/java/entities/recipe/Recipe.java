package entities.recipe;

/**
 * Recipe entity that both meal and cocktail must follow.
 */
public abstract class Recipe {

    /**
     * returns the getName of the recipe.
     * @return getName of recipe.
     */
    public abstract String getName();

    /**
     * returns the getId of the recipe.
     * @return getId of recipe.
     */
    public abstract int getId();

    /**
     * String representation of recipe.
     * @return string repr of recipe.
     */
    public abstract String toString();

    /**
     * Image link of the recipe.
     * @return link of recipe image.
     */
    public abstract String getImageLink();
}
