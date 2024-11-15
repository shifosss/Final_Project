package use_case.signup;

import entities.user.User;

/**
 * DAO of sign up usecase.
 */
public interface SignupDataAccessInterface {
    /**
     * Signs up the user.
     * @param user the user entity.
     */
    void signUp(User user);

    /**
     * Checks if there are existing user with given username.
     * @param username the username.
     * @return true if the username already exists. Otherwise, return false.
     */
    boolean existsByUsername(String username);
}
