package entities.recipe;

/**
 * Ingredient entity.
 */
public class Ingredient {
    // private final int getId;
    private final String name;
    private final String measure;

    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return String.format("Ingredient{getName='%s', measure='%s'}", name, measure);
    }
}
