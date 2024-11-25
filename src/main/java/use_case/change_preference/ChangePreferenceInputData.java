package use_case.change_preference;

import java.util.List;

/**
 * Input data for change preference use case.
 */
public class ChangePreferenceInputData {
    private final List<String> ingredientsToAvoid;

    public ChangePreferenceInputData(List<String> ingredientsToAvoid) {
        this.ingredientsToAvoid = ingredientsToAvoid;
    }

    public List<String> getIngredientsToAvoid() {
        return ingredientsToAvoid;
    }
}
