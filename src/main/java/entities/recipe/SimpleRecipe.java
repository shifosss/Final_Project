package entities.recipe;

/**
 * A simplified version of Recipe entity that only contains
 * essential information for the explore ingredients feature.
 */
public class SimpleRecipe {
    private final String name;
    private final int id;
    private final String imageLink;

    public SimpleRecipe(String name, int id, String imageLink) {
        this.name = name;
        this.id = id;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImageLink() {
        return imageLink;
    }
}