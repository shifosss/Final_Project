package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * Signup view model.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public SignupViewModel() {
        super("sign up");
        setState(new SignupState());
    }
}
