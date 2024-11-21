package use_case.view_recipe;

/**
 * Input data that a recipe detail might need initially to process data.
 */
public class ViewRecipeInputData {
    private final int id;

    public ViewRecipeInputData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
