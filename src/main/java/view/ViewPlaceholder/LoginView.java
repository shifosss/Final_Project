package view.ViewPlaceholder;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import view.PageView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Login view.
 */
public class LoginView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final JTextField usernameTextField = new JTextField(20);
    private final JPasswordField passwordTextField = new JPasswordField(20);
    private final JButton loginButton = new JButton("Login");
    private final JButton switchToSignupButton = new JButton("Switch to sign up");

    private LoginViewModel loginViewModel;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        add(new JLabel("Login Panel"));
        add(usernameTextField);
        add(passwordTextField);
        add(loginButton);
        add(switchToSignupButton);

        final ActionListener attemptLogin = event -> {
            if (event.getSource().equals(loginButton) || event.getSource().equals(usernameTextField)
                    || event.getSource().equals(passwordTextField)) {
                final LoginState currentState = loginViewModel.getState();
                loginController.execute(currentState.getUsername(), currentState.getPassword());
            }
        };

        final ActionListener switchToSignup = event -> {
            if (event.getSource().equals(switchToSignupButton)) {
                loginController.switchToSignupView();
            }
        };

        loginButton.addActionListener(attemptLogin);
        usernameTextField.addActionListener(switchToSignup);
        passwordTextField.addActionListener(switchToSignup);

        switchToSignupButton.addActionListener(switchToSignup);

        addUsernameListener();
        addPasswordListener();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public String getViewName() {
        return "log in";
    }

    private void addUsernameListener() {
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(usernameTextField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addPasswordListener() {
        passwordTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                // TODO: getText is deprecated, find the new method for this.
                currentState.setUsername(passwordTextField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }
}
