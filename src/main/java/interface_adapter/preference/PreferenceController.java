package interface_adapter.preference;

import java.util.List;

import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.change_preference.ChangePreferenceInputData;

/**
 * Controller for the preference view.
 */
public class PreferenceController {
    private final ChangePreferenceInputBoundary changePreferenceInteractor;

    public PreferenceController(ChangePreferenceInputBoundary changePreferenceInteractor) {
        this.changePreferenceInteractor = changePreferenceInteractor;
    }

    /**
     * Changes the preference info of current logged-in user.
     * @param ingredientsToAvoid the list of ingredient names to be avoided.
     */
    public void changePreference(List<String> ingredientsToAvoid) {
        final ChangePreferenceInputData inputData = new ChangePreferenceInputData(ingredientsToAvoid);
        changePreferenceInteractor.changeIngredientsToAvoid(inputData);
    }

    /**
     * Switches to the home view.
     */
    public void switchToHomePageView() {

        changePreferenceInteractor.switchToHomePageView();
    }
}
