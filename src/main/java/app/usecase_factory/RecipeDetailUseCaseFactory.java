package app.usecase_factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailPresenter;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.view_recipe.ViewRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import view.RecipeDetailView;

/**
 * Responsible for creating recipe detail view.
 */
public final class RecipeDetailUseCaseFactory {
    private RecipeDetailUseCaseFactory() {
    }

    /**
     * Creates a recipe detail view.
     * @param viewManagerModel the ViewManagerModel to be injected into the View.
     * @param recipeDetailViewModel the RecipeDetail View Model to be injected into the View.
     * @param searchRecipeViewModel the SearchRecipe View Model to be injected into the View.
     * @param recipeDetailDataAccessObject the RecipeDetail DAO to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View.
     * @return a new instance of recipe detail view.
     */
    public static RecipeDetailView create(ViewManagerModel viewManagerModel,
                                          RecipeDetailViewModel recipeDetailViewModel,
                                          SearchRecipeViewModel searchRecipeViewModel,
                                          ViewRecipeDataAccessInterface recipeDetailDataAccessObject,
                                          ServiceManager serviceManager) {
        final RecipeDetailController recipeDetailController = createRecipeDetailUseCase(viewManagerModel,
                recipeDetailViewModel, searchRecipeViewModel,
                recipeDetailDataAccessObject);
        return new RecipeDetailView(recipeDetailViewModel, recipeDetailController, serviceManager);
    }

    private static RecipeDetailController createRecipeDetailUseCase(
            ViewManagerModel viewManagerModel,
            RecipeDetailViewModel recipeDetailViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            ViewRecipeDataAccessInterface recipeDetailDataAccessObject) {
        final ViewRecipeOutputBoundary recipeDetailOutputBoundary = new RecipeDetailPresenter(
                recipeDetailViewModel, searchRecipeViewModel,
                viewManagerModel
        );
        final ViewRecipeInputBoundary recipeDetailInteractor = new ViewRecipeInteractor(
                recipeDetailDataAccessObject, recipeDetailOutputBoundary
        );
        return new RecipeDetailController(recipeDetailInteractor);
    }
}
