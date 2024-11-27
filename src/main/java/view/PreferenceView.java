package view;

import interface_adapter.preference.PreferenceController;
import interface_adapter.preference.PreferenceState;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.services.ServiceManager;
import view.concrete_page.PreferenceConcrete;
import view.ui_components.preference_view.ButtonPanel;
import view.ui_components.preference_view.IngredientPanel;
import view.ui_components.preference_view.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Preference view that lets the user change their preference.
 */
public class PreferenceView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "preference";
    private final PreferenceController preferenceController;
    private final PreferenceViewModel preferenceViewModel;
    private final ServiceManager serviceManager;

    private final JButton goHomeButton;
    private final JButton updatePreferenceButton;

    private final PageView<PreferenceState> pageHandler;

    private final List<String> ingredientsChosen = new ArrayList<>();

    public PreferenceView(PreferenceController preferenceController,
                          PreferenceViewModel preferenceViewModel,
                          ServiceManager serviceManager) {
        this.preferenceController = preferenceController;
        this.preferenceViewModel = preferenceViewModel;
        this.serviceManager = serviceManager;

        preferenceViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());
        setBackground(new Color(40, 40, 40)); // Dark gray background

        goHomeButton = new JButton("Go Home");
        updatePreferenceButton = new JButton("Update Preference");

        final PreferenceConcrete preferenceConcrete = new PreferenceConcrete();
        final TitlePanel titlePanel = new TitlePanel(preferenceConcrete);
        final IngredientPanel ingredientPanel = new IngredientPanel(
                titlePanel, preferenceController, updatePreferenceButton);
        final ButtonPanel buttonPanel = new ButtonPanel(ingredientPanel, goHomeButton, updatePreferenceButton);
        pageHandler = buttonPanel;

        goHomeButton.addActionListener(event -> preferenceController.switchToHomePageView());
        updatePreferenceButton.addActionListener(event -> preferenceController.changePreference(ingredientsChosen));

        add(titlePanel, BorderLayout.NORTH);
        add(ingredientPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PreferenceState state = preferenceViewModel.getState();
        pageHandler.update(state);
    }

    public String getViewName() {
        return viewName;
    }
}
