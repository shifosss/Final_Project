package use_case.change_preference;

import java.util.List;

/**
 * DAO for the change preference usecase.
 */
public interface ChangePreferenceDataAccessInterface {
    /**
     * Updates the ingredients to avoid.
     * @param username the username.
     * @param ingredientsToAvoid ingredients to avoid.
     */
    void changeIngredientsToAvoid(String username, List<String> ingredientsToAvoid);

    /**
     * Gets the current username of the app.
     * @return the username.
     */
    String getCurrentUser();
}
