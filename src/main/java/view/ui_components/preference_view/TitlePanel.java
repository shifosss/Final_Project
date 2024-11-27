package view.ui_components.preference_view;

import interface_adapter.preference.PreferenceState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Title panel for the preference view.
 */
public class TitlePanel extends AbstractViewDecorator<PreferenceState> {
    public TitlePanel(PageView<PreferenceState> tempPage) {
        super(tempPage);
    }

    @Override
    public void update(PreferenceState state) {
        super.getTempPage().update(state);
    }
}
