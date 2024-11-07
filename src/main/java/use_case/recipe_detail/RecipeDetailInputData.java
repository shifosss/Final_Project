package use_case.recipe_detail;

/**
 * Input data that a recipe detail might need initially to process data.
 */
public class RecipeDetailInputData {
    private final int id;

    public RecipeDetailInputData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
