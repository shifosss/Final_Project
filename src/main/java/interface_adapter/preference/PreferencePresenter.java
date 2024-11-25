package interface_adapter.preference;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import use_case.change_preference.ChangePreferenceOutputBoundary;
import use_case.change_preference.ChangePreferenceOutputData;

/**
 * Presenter for the preference view.
 */
public class PreferencePresenter implements ChangePreferenceOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PreferenceViewModel preferenceViewModel;
    private final HomePageViewModel homePageViewModel;

    public PreferencePresenter(ViewManagerModel viewManagerModel,
                               PreferenceViewModel preferenceViewModel,
                               HomePageViewModel homePageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.preferenceViewModel = preferenceViewModel;
        this.homePageViewModel = homePageViewModel;
    }

    @Override
    public void prepareSuccess(ChangePreferenceOutputData outputData) {

    }

    @Override
    public void switchToHomeView() {

    }
}
