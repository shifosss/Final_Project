package view.ui_components.recipe_detail;

import interface_adapter.recipe_detail.RecipeDetailState;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the back button and bookmark button.
 */
public class NavigationActionPanel extends AbstractViewDecorator<RecipeDetailState> {
    private final JButton backButton;
    private final JButton bookmarkButton;

    public NavigationActionPanel(PageView<RecipeDetailState> view, JButton backButton, JButton bookmarkButton) {
        super(view);
        this.backButton = backButton;
        this.bookmarkButton = bookmarkButton;
        setLayout(new BorderLayout());

        backButton.setOpaque(true);
        bookmarkButton.setOpaque(true);
        bookmarkButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add buttons to their respective positions
        add(backButton, BorderLayout.WEST);
        add(bookmarkButton, BorderLayout.EAST);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);
        // if the user has bookmarked this recipe.
        if (state.getIsBookmarked()) {
            bookmarkButton.setText("Bookmarked");
            bookmarkButton.setBackground(Color.LIGHT_GRAY);
        }
        else {
            bookmarkButton.setText("Bookmark");
            bookmarkButton.setBackground(Color.YELLOW);
        }
    }
}
