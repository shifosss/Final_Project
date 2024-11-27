package view.views_placeholder;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import view.PageView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Login view with background and cocktail images.
 */
public class LoginView extends JPanel implements PageView<LoginState>, ActionListener, PropertyChangeListener {
    private final String viewName = "log in";
    private final JTextField usernameTextField = new JTextField(20);
    private final JPasswordField passwordTextField = new JPasswordField(20);
    private final JButton loginButton = new JButton("Login");
    private final JButton switchToSignupButton = new JButton("Switch to sign up");

    private LoginViewModel loginViewModel;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        loginViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // add title
        final JLabel titleLabel = new JLabel("Cocktail Lab");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // add username frame
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameTextField, gbc);

        // add password input frame
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordTextField, gbc);

        // add login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);

        // add signup button
        gbc.gridx = 1;
        add(switchToSignupButton, gbc);

        loginButton.setBackground(new Color(255, 87, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(new Color(255, 140, 0));
            }

            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(new Color(255, 87, 34));
            }
        });

        switchToSignupButton.setBackground(new Color(75, 0, 130));
        switchToSignupButton.setForeground(Color.WHITE);

        // add action listener
        loginButton.addActionListener(event -> {
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });

        switchToSignupButton.addActionListener(event -> loginController.switchToSignupView());

        addUsernameListener();
        addPasswordListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // paint background color
        g.setColor(new Color(255, 165, 0)); // orange background
        g.fillRect(0, 0, getWidth(), getHeight());

        // load png image
        ImageIcon cocktail1 = new ImageIcon(getClass().getResource("/image/cocktail1.png"));
        g.drawImage(cocktail1.getImage(), 50, 50, 200, 300, this);

        ImageIcon cocktail2 = new ImageIcon(getClass().getResource("/image/cocktail2.png"));
        g.drawImage(cocktail2.getImage(), 550, 50, 100, 150, this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public String getViewName() {
        return viewName;
    }

    @Override
    public void update(LoginState state) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = loginViewModel.getState();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameTextField.setText(state.getUsername());
        passwordTextField.setText(state.getPassword());
        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
        }
    }

    private void addUsernameListener() {
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameTextField.getText());
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
                currentState.setPassword(passwordTextField.getText());
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
