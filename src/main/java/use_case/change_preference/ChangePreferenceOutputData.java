package use_case.change_preference;

import java.util.List;

/**
 * OutputData for the change preference.
 */
public class ChangePreferenceOutputData {
    private final List<String> ingredients;
    private final boolean useCaseFailed;

    public ChangePreferenceOutputData(List<String> ingredients,
                                      boolean useCaseFailed) {
        this.ingredients = ingredients;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<String> getIngredients() {
        return null;
    }
}
