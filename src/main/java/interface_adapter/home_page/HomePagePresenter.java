package interface_adapter.home_page;

import interface_adapter.ViewManagerModel;

public class HomePagePresenter {
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomePagePresenter(
            HomePageViewModel homePageViewModel,
            ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareView(String viewName) {
        viewManagerModel.setState(viewName);
        viewManagerModel.firePropertyChanged();
    }
}