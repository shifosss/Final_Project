package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.preference.PreferenceController;
import interface_adapter.preference.PreferencePresenter;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.services.ServiceManager;
import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.change_preference.ChangePreferenceInteractor;
import use_case.change_preference.ChangePreferenceOutputBoundary;
import view.PreferenceView;

/**
 * Change preference use case factory.
 */
public final class ChangePreferenceUseCaseFactory {

    private ChangePreferenceUseCaseFactory() {
    }

    /**
     * Creates the preference view and injects all the necessary usecase.
     * @param viewManagerModel the ViewManagerModel to be injected into the view.
     * @param homePageViewModel the HomePageViewModel to be injected into the view.
     * @param preferenceViewModel the PreferenceViewModel to be injected into the view.
     * @param cocktailDataAccessObject the CocktailDataAccessObject to be injected into the view.
     * @param userDataAccessObject the UserDataAccessObject to be injected into the view.
     * @param serviceManager the ServiceManager to be injected into the view.
     * @return the view.
     */
    public static PreferenceView create(ViewManagerModel viewManagerModel,
                                 HomePageViewModel homePageViewModel,
                                 PreferenceViewModel preferenceViewModel,
                                 CocktailDataAccessObject cocktailDataAccessObject,
                                 UserDataAccessObject userDataAccessObject,
                                 ServiceManager serviceManager) {
        final PreferenceController preferenceController = createPreferenceUseCases(
                viewManagerModel, homePageViewModel, preferenceViewModel,
                cocktailDataAccessObject, userDataAccessObject);
        return new PreferenceView(preferenceController, preferenceViewModel, serviceManager);
    }

    private static PreferenceController createPreferenceUseCases(ViewManagerModel viewManagerModel,
                                                         HomePageViewModel homePageViewModel,
                                                         PreferenceViewModel preferenceViewModel,
                                                         CocktailDataAccessObject cocktailDataAccessObject,
                                                         UserDataAccessObject userDataAccessObject) {
        final ChangePreferenceOutputBoundary changePreferenceOutputBoundary = new PreferencePresenter(
                viewManagerModel, preferenceViewModel, homePageViewModel
        );
        final ChangePreferenceInputBoundary changePreferenceInteractor = new ChangePreferenceInteractor(
                changePreferenceOutputBoundary, userDataAccessObject, cocktailDataAccessObject);

        return new PreferenceController(changePreferenceInteractor);
    }
}
