package entities.mealrecipe;

public class Ingredient {
    private String name;
    private String id;

    public Ingredient(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
