package use_case.change_preference;

/**
 * Output Boundary for the change preference.
 */
public interface ChangePreferenceOutputBoundary {
    /**
     * Shows the view when the change was successful.
     * @param outputData the output data.
     */
    void prepareSuccess(ChangePreferenceOutputData outputData);

    /**
     * Switches to the home view.
     */
    void switchToHomePageView();

    /**
     * Switches to the Preference View.
     * @param outputData the output data.
     */
    void switchToPreferenceView(ChangePreferenceOutputData outputData);
}
