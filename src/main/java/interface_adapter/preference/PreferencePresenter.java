package interface_adapter.preference;


import interface_adapter.ViewManagerModel;
import use_case.change_preference.ChangePreferenceOutputBoundary;
import use_case.change_preference.ChangePreferenceOutputData;
import interface_adapter.home_page.HomePageViewModel;

/**
 * Presenter for managing preference update results.
 */
public class PreferencePresenter implements ChangePreferenceOutputBoundary {
    private final PreferenceViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomePageViewModel homePageViewModel;
    public PreferencePresenter(PreferenceViewModel viewModel, ViewManagerModel viewManagerModel,
                               HomePageViewModel homePageViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.homePageViewModel = homePageViewModel;
    }

    @Override
    public void prepareSuccess(ChangePreferenceOutputData outputData) {
        if (!outputData.isUseCaseFailed()) {
            viewManagerModel.setState(viewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState("home");
        viewManagerModel.firePropertyChanged();
    }
}