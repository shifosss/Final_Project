package view;

import data_access.DBUserDataAccessObject;
import data_access.UserDataAccessObject;
import entities.user.User;
import entities.user.factory.UserFactory;
import entities.user.factory.CommonUserFactory;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainPage extends JPanel implements PropertyChangeListener {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final CardLayout parentCardLayout;
    private final JPanel parentPanel;

    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final LoginController loginController;
    private final SignupController signupController;

    private List<JLabel> imageLabels;
    private List<List<ImageIcon>> imageGroups;
    private int currentImageIndex = 0;

    private JPanel buttonPanel;
    private JButton loginButton;
    private JButton signupButton;
    private JButton logoutButton;

    public MainPage(CardLayout parentCardLayout, JPanel parentPanel, LoginViewModel loginViewModel,
                    SignupViewModel signupViewModel,
                    LoginController loginController,
                    SignupController signupController) {
        this.parentCardLayout = parentCardLayout;
        this.parentPanel = parentPanel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.loginController = loginController;
        this.signupController = signupController;

        // Register for updates
        loginViewModel.addPropertyChangeListener((PropertyChangeListener) this);
        signupViewModel.addPropertyChangeListener((PropertyChangeListener) this);

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        initializeHeader();
        initializeImagePanel();
        initializeButtonPanel();
    }

    private void initializeHeader() {
        JLabel headerLabel = new JLabel("cocktail lab");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);
    }

    private void initializeImagePanel() {
        JPanel imagesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        imagesPanel.setBackground(BACKGROUND_COLOR);
        add(imagesPanel, BorderLayout.CENTER);

        imageGroups = loadImageGroups();
        imageLabels = new ArrayList<>();

        for (int i = 0; i < imageGroups.size(); i++) {
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            imageLabels.add(imageLabel);
            imagesPanel.add(imageLabel);
        }

        startImageSlideshow();
    }

    private void initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton = createLoginButton();
        signupButton = createSignupButton();
        logoutButton = createLogoutButton();

        updateButtonPanel(false);
    }

    private void updateButtonPanel(boolean isLoggedIn) {
        buttonPanel.removeAll();
        if (isLoggedIn) {
            buttonPanel.add(logoutButton);
        } else {
            buttonPanel.add(loginButton);
            buttonPanel.add(signupButton);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, LoginViewModel.USERNAME_LABEL);
            if (username != null && !username.trim().isEmpty()) {
                String password = JOptionPane.showInputDialog(this, LoginViewModel.PASSWORD_LABEL);
                if (password != null && !password.trim().isEmpty()) {
                    loginController.execute(username.trim(), password);
                }
            }
        });
        return loginButton;
    }

    private JButton createLogoutButton() {
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            LoginState newState = new LoginState();
            newState.setLoggedIn(false);
            loginViewModel.setState(newState);

            parentCardLayout.show(parentPanel, "main");
            updateButtonPanel(false);

            JOptionPane.showMessageDialog(this,
                    "Logged out successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        return logoutButton;
    }

    private JButton createSignupButton() {
        JButton signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signupButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, SignupViewModel.USERNAME_LABEL);
            if (username != null && !username.trim().isEmpty()) {
                String password = JOptionPane.showInputDialog(this, SignupViewModel.PASSWORD_LABEL);
                if (password != null && !password.trim().isEmpty()) {
                    signupController.execute(username.trim(), password);
                }
            }
        });
        return signupButton;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            if (evt.getSource() == loginViewModel) {
                LoginState state = loginViewModel.getState();
                if (state.isLoggedIn()) {
                    JOptionPane.showMessageDialog(this,
                            "Login successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    updateButtonPanel(true);
                    parentCardLayout.show(parentPanel, "mainAppPage");
                } else if (!state.getError().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            state.getError(),
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (evt.getSource() == signupViewModel) {
                SignupState state = signupViewModel.getState();
                if (state.isCreated()) {
                    JOptionPane.showMessageDialog(this,
                            "Account created successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    loginController.execute(state.getUsername(), state.getPassword());
                } else if (!state.getError().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            state.getError(),
                            "Signup Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private List<List<ImageIcon>> loadImageGroups() {
        List<List<ImageIcon>> imageGroups = new ArrayList<>();

        // define image groups
        List<ImageIcon> group1 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP.jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (9).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (2).jpg"))
        );
        List<ImageIcon> group2 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP (3).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (4).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (5).jpg"))
        );
        List<ImageIcon> group3 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP (6).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (7).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (8).jpg"))
        );

        // add image group to imageGroups
        imageGroups.add(group1);
        imageGroups.add(group2);
        imageGroups.add(group3);

        return imageGroups;
    }

    private void startImageSlideshow() {
        // set timer to 2 sec each photo
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 循环切换每个
                for (int i = 0; i < imageLabels.size(); i++) {
                    List<ImageIcon> images = imageGroups.get(i);
                    JLabel imageLabel = imageLabels.get(i);
                    imageLabel.setIcon(images.get(currentImageIndex % images.size()));
                }
                currentImageIndex++;
            }
        }, 0, 10000); // each 2 second change a photo
    }
}

