package use_case.create_recipe;

/**
 * Interactor for the custom recipe usecase.
 */
public class CustomRecipeInteractor implements CustomRecipeInputBoundary {
    private final CustomRecipeDataAccessInterface customRecipeDataAccessObject;
    private final CustomRecipeOutputBoundary customRecipePresenter;

    public CustomRecipeInteractor(CustomRecipeDataAccessInterface customRecipeDataAccessObject,
                                  CustomRecipeOutputBoundary customRecipePresenter) {
        this.customRecipeDataAccessObject = customRecipeDataAccessObject;
        this.customRecipePresenter = customRecipePresenter;
    }

    @Override
    public void switchToHomePage() {
    }

    @Override
    public void switchToCustomRecipeView() {

    }

    @Override
    public void saveCustomRecipe(CustomRecipeInputData inputData) {

    }
}
