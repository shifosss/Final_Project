package interface_adapter.home_page;

import interface_adapter.ViewModel;

/**
 * The view model for the home page.
 */
public class HomePageViewModel extends ViewModel<HomePageState> {
    public HomePageViewModel() {
        super("home page");
        setState(new HomePageState());
    }
}
