package domain.entities.user.factory;

import domain.entities.user.CommonUser;
import domain.entities.user.User;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }
}
