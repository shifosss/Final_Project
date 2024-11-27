package view.concrete_page;

import interface_adapter.home_page.HomePageState;
import view.PageView;

/**
 * Concrete template for the home page.
 */
public class HomeConcrete implements PageView<HomePageState> {
    @Override
    public void update(HomePageState state) {
        System.out.println("Home View updated");
    }
}
