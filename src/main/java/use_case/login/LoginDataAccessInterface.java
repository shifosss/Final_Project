package use_case.login;

import entities.user.User;

import java.util.List;

/**
 * DAO for the login use case.
 */
public interface LoginDataAccessInterface {
    /**
     * Checks if the username exists within the database.
     * @param username the username.
     * @return true if the username matches with any user. Otherwise, return false.
     */
    boolean existsByName(String username);

    /**
     * Gets the user data from database that matches with the username.
     * @param username the username.
     * @return a user entity based on username.
     */
    User getUser(String username);

    /**
     * Sets the current user.
     * @param username the username.
     */
    void setCurrentUser(String username);

    /**
     * Gets the current username.
     * @return the current username.
     */
    String getCurrentUser();

    /**
     * Returns the ingredients the user does not want on their food.
     * @param username the username.
     * @return a list of ingredients id to avoid.
     */
    List<Integer> getIngredientsToAvoid(String username);

    /**
     * Gets the bookmarked recipes of user.
     * @param username the user.
     * @return a list of recipe ids.
     */
    List<Integer> getBookmarkedRecipes(String username);
}
