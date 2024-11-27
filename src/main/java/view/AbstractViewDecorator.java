package view;

import javax.swing.*;

/**
 * Decorator for gui components of a view.
 * @param <T> the state type.
 */
public abstract class AbstractViewDecorator<T> extends JPanel implements PageView<T> {
    private final PageView<T> tempPage;

    protected AbstractViewDecorator(PageView<T> tempPage) {
        this.tempPage = tempPage;
    }

    public PageView<T> getTempPage() {
        return tempPage;
    }

    /**
     * Updates the component.
     * @param state the view state.
     */
    public void update(T state) {
        tempPage.update(state);
    }
}
