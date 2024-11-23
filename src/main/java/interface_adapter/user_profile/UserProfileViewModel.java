package interface_adapter.user_profile;

import interface_adapter.ViewModel;

/**
 * The viewmodel for the account view usecase.
 */
public class UserProfileViewModel extends ViewModel<UserProfileState> {
    public UserProfileViewModel() {
        super("account");
        setState(new UserProfileState());
    }
}
