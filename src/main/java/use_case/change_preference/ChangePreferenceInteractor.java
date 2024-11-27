package use_case.change_preference;

import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;

import java.util.List;

/**
 * Interactor for the change preference usecase.
 */
public class ChangePreferenceInteractor implements ChangePreferenceInputBoundary {
    private final ChangePreferenceOutputBoundary changePreferencePresenter;
    private final ChangePreferenceDataAccessInterface changePreferenceDataAccessObject;
    private final ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject;

    public ChangePreferenceInteractor(ChangePreferenceOutputBoundary changePreferencePresenter,
                                      ChangePreferenceDataAccessInterface changePreferenceDataAccessObject,
                                      ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject) {
        this.changePreferencePresenter = changePreferencePresenter;
        this.changePreferenceDataAccessObject = changePreferenceDataAccessObject;
        this.exploreIngredientDataAccessObject = exploreIngredientDataAccessObject;
    }

    @Override
    public void changeIngredientsToAvoid(ChangePreferenceInputData inputData) {
        final String username = changePreferenceDataAccessObject.getCurrentUser();
        final List<String> ingredientsToAvoid = inputData.getIngredientsToAvoid();

        changePreferenceDataAccessObject.changeIngredientsToAvoid(username, ingredientsToAvoid);
        final ChangePreferenceOutputData outputData = new ChangePreferenceOutputData(null, false);
        changePreferencePresenter.prepareSuccess(outputData);
    }

    @Override
    public void switchToHomePageView() {
        changePreferencePresenter.switchToHomePageView();
    }

    @Override
    public void switchToPreferenceView() {
        final List<String> ingredients = exploreIngredientDataAccessObject.getIngredientsList();
        final ChangePreferenceOutputData outputData = new ChangePreferenceOutputData(ingredients, false);
        changePreferencePresenter.switchToPreferenceView(outputData);
    }
}
