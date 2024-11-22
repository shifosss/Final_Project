package view;

/**
 * Represents the pages that can be viewed (one at a time).
 */
public interface PageView {
    /**
     * Returns the view getName (identifier so pages can be switched efficiently.
     * @return the view getName.
     */
    String getViewName();
}
