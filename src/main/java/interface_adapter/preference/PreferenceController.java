package interface_adapter.preference;

import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.change_preference.ChangePreferenceInputData;

import java.util.List;

/**
 * Controller for managing user preferences related to excluded ingredients.
 */
public class PreferenceController {
    private final ChangePreferenceInputBoundary interactor;
    private final PreferenceViewModel viewModel;

    public PreferenceController(ChangePreferenceInputBoundary interactor, PreferenceViewModel viewModel) {
        this.interactor = interactor;
        this.viewModel = viewModel;
    }

    /**
     * Updates the user's preferences by avoiding specific ingredients.
     *
     * @param ingredientsToAvoid the list of ingredients to avoid.
     */
    public void updatePreferences(List<String> ingredientsToAvoid) {
        ChangePreferenceInputData inputData = new ChangePreferenceInputData(ingredientsToAvoid);
        interactor.changeIngredientsToAvoid(inputData);
    }

    /**
     * switch to the home view after updating preferences.
     */
    public void navigateToHome() {
        interactor.switchToHome();
    }
}
