package view.ui_components.preference_view;

import interface_adapter.preference.PreferenceState;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;

/**
 * Title panel for the preference view.
 */
public class TitlePanel extends AbstractViewDecorator<PreferenceState> {
    private final JLabel descriptionLabel;

    public TitlePanel(PageView<PreferenceState> tempPage) {
        super(tempPage);
        descriptionLabel = new JLabel("Update Preference");
        add(descriptionLabel);
    }

    @Override
    public void update(PreferenceState state) {
        super.getTempPage().update(state);
        descriptionLabel.setText(String.format("Update Preference: %s", state.getUsername()));
    }
}
