package view.views_placeholder;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import view.PageView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for sign up with enhanced design.
 */
public class SignupView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final JButton signupButton = new JButton("Sign up");
    private final JButton loginButton = new JButton("Switch to Login");
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField confirmPasswordField = new JPasswordField(20);

    private SignupController signupController;
    private SignupViewModel signupViewModel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        setLayout(new GridBagLayout());
        setOpaque(false); // 背景透明以显示自定义背景图
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // add title
        JLabel titleLabel = new JLabel("Cocktail lab");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // add user input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        // add password frame
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        // add password input frame
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        add(confirmPasswordField, gbc);

        // add signup button
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(signupButton, gbc);

        // add login button
        gbc.gridx = 1;
        add(loginButton, gbc);

        // set up button
        signupButton.setBackground(new Color(0, 128, 0));
        signupButton.setForeground(Color.WHITE);

        loginButton.setBackground(new Color(75, 0, 130));
        loginButton.setForeground(Color.WHITE);

        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(34, 139, 34));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(0, 128, 0));
            }
        });

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(139, 0, 139));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(75, 0, 130));
            }
        });

        // add actionlistener
        signupButton.addActionListener(event -> {
            final SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword()
            );
        });

        loginButton.addActionListener(event -> signupController.switchToLoginView());

        addUsernameListener();
        addPasswordListener();
        addConfirmPasswordListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // paint background
        g.setColor(new Color(255, 165, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        // load png image
        ImageIcon cocktail1 = new ImageIcon(getClass().getResource("/image/cocktail1.png"));
        g.drawImage(cocktail1.getImage(), 50, 50, 200, 300, this);

        ImageIcon cocktail2 = new ImageIcon(getClass().getResource("/image/cocktail2.png"));
        g.drawImage(cocktail2.getImage(), 550, 50, 100, 150, this);
    }

    @Override
    public String getViewName() {
        return "sign up";
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    private void addUsernameListener() {
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameField.getText());
                signupViewModel.setState(currentState);
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
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()));
                signupViewModel.setState(currentState);
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

    private void addConfirmPasswordListener() {
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(confirmPasswordField.getPassword()));
                signupViewModel.setState(currentState);
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