package view.ViewPlaceholder;

import view.PageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public String getViewName() {
        return "home page";
    }
}
