package use_case.view_recipe;

/**
 * Input data that a meal recipe detail might need initially to process data.
 */

public class ViewMealRecipeInputData {

    private final String id;

    public ViewMealRecipeInputData(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

}

