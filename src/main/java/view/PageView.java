package view;

/**
 * Represents the pages that can be viewed (one at a time).
 */
public interface PageView<T> {
    /**
     * Updates the component.
     * @param state the view state.
     */
    void update(T state);
}
