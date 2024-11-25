package use_case.explore_ingredient;

import java.util.List;

/**
 * Output data for the explore ingredient usecase.
 */
public class ExploreIngredientOutputData {
    private final List<String> ingredientsList;
    private final boolean useCaseFailed;

    public ExploreIngredientOutputData(List<String> ingredientsList, boolean useCaseFailed) {
        this.ingredientsList = ingredientsList;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getIngredientsList() {
        return ingredientsList;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}