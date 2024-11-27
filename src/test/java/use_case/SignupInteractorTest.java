package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.user.User;
import entities.user.factory.UserFactory;
import use_case.signup.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupInteractorTest {
    private SignupDataAccessInterface dataAccess;
    private SignupOutputBoundary presenter;
    private UserFactory userFactory;
    private SignupInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(SignupDataAccessInterface.class);
        presenter = mock(SignupOutputBoundary.class);
        userFactory = mock(UserFactory.class);
        interactor = new SignupInteractor(dataAccess, presenter, userFactory);
    }

    @Test
    void execute_Success() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        SignupInputData inputData = new SignupInputData(username, password, password);
        User mockUser = mock(User.class);
        when(mockUser.getName()).thenReturn(username);

        when(dataAccess.existsByName(username)).thenReturn(false);
        when(userFactory.create(username, password)).thenReturn(mockUser);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(dataAccess).existsByName(username);
        verify(userFactory).create(username, password);
        verify(dataAccess).signUp(mockUser);
        verify(presenter).prepareSuccessView(any(SignupOutputData.class));
        verify(presenter, never()).prepareFailView(anyString());
    }

    @Test
    void execute_UserAlreadyExists() {
        // Arrange
        String username = "existingUser";
        String password = "password123";
        SignupInputData inputData = new SignupInputData(username, password, password);

        when(dataAccess.existsByName(username)).thenReturn(true);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(dataAccess).existsByName(username);
        verify(presenter).prepareFailView("User already exists.");
        verify(presenter, never()).prepareSuccessView(any());
        verify(dataAccess, never()).signUp(any());
        verify(userFactory, never()).create(anyString(), anyString());
    }

    @Test
    void execute_PasswordsDontMatch() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        String repeatPassword = "differentPassword";
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);

        when(dataAccess.existsByName(username)).thenReturn(false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(dataAccess).existsByName(username);
        verify(presenter).prepareFailView("Passwords don't match.");
        verify(presenter, never()).prepareSuccessView(any());
        verify(dataAccess, never()).signUp(any());
        verify(userFactory, never()).create(anyString(), anyString());
    }

    @Test
    void switchToLoginView_Success() {
        // Act
        interactor.switchToLoginView();

        // Assert
        verify(presenter).switchToLoginView();
    }
}

class SignupInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        String repeatPassword = "password123";

        // Act
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(password, inputData.getPassword());
        assertEquals(repeatPassword, inputData.getRepeatPassword());
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String username = "";
        String password = "";
        String repeatPassword = "";

        // Act
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(password, inputData.getPassword());
        assertEquals(repeatPassword, inputData.getRepeatPassword());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String username = null;
        String password = null;
        String repeatPassword = null;

        // Act
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);

        // Assert
        assertNull(inputData.getUsername());
        assertNull(inputData.getPassword());
        assertNull(inputData.getRepeatPassword());
    }
}

class SignupOutputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        boolean useCaseFailed = false;

        // Act
        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithEmptyUsername() {
        // Arrange
        String username = "";
        boolean useCaseFailed = true;

        // Act
        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullUsername() {
        // Arrange
        String username = null;
        boolean useCaseFailed = false;

        // Act
        SignupOutputData outputData = new SignupOutputData(username, useCaseFailed);

        // Assert
        assertNull(outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
    }
}