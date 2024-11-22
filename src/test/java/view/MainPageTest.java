package view;

import javax.swing.*;
import java.awt.*;

import data_access.DBUserDataAccessObject;
import entities.user.factory.CommonUserFactory;
import entities.user.factory.UserFactory;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;

public class MainPageTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Page Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Initialize the ViewModels
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // Initialize the user related components
        UserFactory userFactory = new CommonUserFactory();
        DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);


        // Initialize the presenters
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        SignupPresenter signupPresenter = new SignupPresenter(signupViewModel);

        // Initialize the use case interactors
        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject,
                loginPresenter
        );

        SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject,
                signupPresenter,
                userFactory
        );

        // Initialize the controllers
        LoginController loginController = new LoginController(loginInteractor);
        SignupController signupController = new SignupController(signupInteractor);

        CardLayout cardLayout = new CardLayout();
        JPanel parentPanel = new JPanel(cardLayout);
        MainPage mainPage = new MainPage(cardLayout, parentPanel,
                loginViewModel,
                signupViewModel,
                loginController,
                signupController);
        parentPanel.add(mainPage, "main");

        frame.add(parentPanel);

        frame.setVisible(true);
    }
}