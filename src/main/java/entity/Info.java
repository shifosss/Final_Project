package entity;

/**
 * Info class that holds information about the recipe.
 */
public class Info {
    private final String id;
    private final String name;
    private final String category;
    private final String area;

    public Info(String id, String name, String category, String area) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }
}
