package domain.entities.recipe;

/**
 * Ingredient entity.
 */
public class Ingredient {
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
        return String.format("Ingredient{name='%s', measure='%s'}", name, measure);
    }
}
