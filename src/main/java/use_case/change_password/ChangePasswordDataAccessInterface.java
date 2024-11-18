package use_case.change_password;

import entities.user.User;

/**
 * Data Access Object fot the change password usecase.
 */
public interface ChangePasswordDataAccessInterface {

    /**
     * Changes the password of the user. Assume that the user has given privilege to perform this.
     * @param user the user entity.
     * @param newPassword the new password to be changed.
     */
    void changePassword(User user, String newPassword);
}
