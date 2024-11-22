package entities.user.factory;

import entities.user.User;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the getName of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String password);

}
