package use_case.change_preference;

/**
 * Input Boundary for the change preference usecase.
 */
public interface ChangePreferenceInputBoundary {
    /**
     * Attempts to change the preference infos of user.
     * @param inputData the input data.
     */
    void changeIngredientsToAvoid(ChangePreferenceInputData inputData);

    /**
     * Switches to the home view.
     */
    void switchToHomePageView();

    /**
     * Switches to the preference view.
     */
    void switchToPreferenceView();
}
