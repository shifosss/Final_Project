package interface_adapter.preference;

import java.util.List;

import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.change_preference.ChangePreferenceInputData;

/**
 * Controller for managing user preferences related to excluded ingredients.
 */
public class PreferenceController {
    private final ChangePreferenceInputBoundary changePreferenceInteractor;

    public PreferenceController(ChangePreferenceInputBoundary changePreferenceInteractor) {
        this.changePreferenceInteractor = changePreferenceInteractor;
    }

    /**
     * Updates the user's preferences by avoiding specific ingredients.
     *
     * @param ingredientsToAvoid the list of ingredients to avoid.
     */
    public void changePreference(List<String> ingredientsToAvoid) {
        final ChangePreferenceInputData inputData = new ChangePreferenceInputData(ingredientsToAvoid);
        changePreferenceInteractor.changeIngredientsToAvoid(inputData);
    }

    /**
     * Switch to the home view after updating preferences.
     */
    public void switchToHomePageView() {

        changePreferenceInteractor.switchToHomePageView();
    }
}
