package view.ui_components.preference_view;

import interface_adapter.preference.PreferenceState;
import view.AbstractViewDecorator;
import view.PageView;

/**
 * Ingredient panel that contains the list of ingredients.
 */
public class IngredientPanel extends AbstractViewDecorator<PreferenceState> {
    public IngredientPanel(PageView<PreferenceState> tempPage) {
        super(tempPage);
    }

    @Override
    public void update(PreferenceState state) {
        super.getTempPage().update(state);
    }
}
