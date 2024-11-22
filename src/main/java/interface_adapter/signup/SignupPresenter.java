package interface_adapter.signup;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;

    public SignupPresenter(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsername(user.getUsername());
        signupState.setCreated(true);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setError(error);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {

    }
}