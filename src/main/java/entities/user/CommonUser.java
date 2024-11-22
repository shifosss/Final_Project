package entities.user;

/**
 * Representation of Common User.
 */
public class CommonUser implements User {
    private final String name;
    private final String password;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("%s with pass: %s", name, password);
    }
}
