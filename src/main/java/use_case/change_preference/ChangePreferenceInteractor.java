package use_case.change_preference;

import java.util.List;

/**
 * Interactor for the change preference usecase.
 */
public class ChangePreferenceInteractor implements ChangePreferenceInputBoundary {
    private final ChangePreferenceOutputBoundary changePreferencePresenter;
    private final ChangePreferenceDataAccessInterface changePreferenceDataAccessObject;

    public ChangePreferenceInteractor(ChangePreferenceOutputBoundary changePreferencePresenter,
                                      ChangePreferenceDataAccessInterface changePreferenceDataAccessObject) {
        this.changePreferencePresenter = changePreferencePresenter;
        this.changePreferenceDataAccessObject = changePreferenceDataAccessObject;
    }

    @Override
    public void changeIngredientsToAvoid(ChangePreferenceInputData inputData) {
        final String username = changePreferenceDataAccessObject.getCurrentUser();
        final List<String> ingredientsToAvoid = inputData.getIngredientsToAvoid();

        changePreferenceDataAccessObject.changeIngredientsToAvoid(username, ingredientsToAvoid);
//        final ChangePreferenceOutputData outputData = new ChangePreferenceOutputData();
//        changePreferencePresenter.prepareSuccess(outputData);
    }

    @Override
    public void switchToHome() {
        changePreferencePresenter.switchToHomeView();
    }
}