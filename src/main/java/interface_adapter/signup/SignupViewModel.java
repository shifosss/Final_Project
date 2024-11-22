package interface_adapter.signup;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String SIGNUP_BUTTON_LABEL = "Create Account";

    private SignupState state = new SignupState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SignupViewModel() {
        super("signup");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    public SignupState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}