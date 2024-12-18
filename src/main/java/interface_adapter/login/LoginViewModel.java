package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * Login view model.
 */
public class LoginViewModel extends ViewModel<LoginState> {
    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }
}
