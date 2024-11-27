package view.ui_components.preference_view;

import interface_adapter.preference.PreferenceState;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;

/**
 * ButtonPanel creates a panel with "Save" and "Back to Home" buttons.
 */
public class ButtonPanel extends AbstractViewDecorator<PreferenceState> {

    private final JButton goHomeButton;
    private final JButton updatePreferenceButton;

    public ButtonPanel(PageView<PreferenceState> tempPage, JButton goHomeButton, JButton updatePreferenceButton) {
        super(tempPage);

        this.goHomeButton = goHomeButton;
        this.updatePreferenceButton = updatePreferenceButton;

        add(goHomeButton);
        add(updatePreferenceButton);
    }

    @Override
    public void update(PreferenceState state) {
        super.getTempPage().update(state);
    }
}
