package view.concrete_page;

import interface_adapter.preference.PreferenceState;
import view.PageView;

/**
 * Concrete preference template.
 */
public class PreferenceConcrete implements PageView<PreferenceState> {

    @Override
    public void update(PreferenceState state) {
        System.out.println("Preference View updated");
    }
}