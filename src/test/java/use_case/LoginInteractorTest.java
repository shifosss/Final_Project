package use_case;

import entities.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.user.User;
import use_case.login.*;
import use_case.random_recipes.RandomRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.user.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginInteractorTest {
    private LoginDataAccessInterface userDataAccess;
    private RandomRecipeDataAccessInterface recipeDataAccess;
    private LoginOutputBoundary loginPresenter;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(LoginDataAccessInterface.class);
        recipeDataAccess = mock(RandomRecipeDataAccessInterface.class);
        loginPresenter = mock(LoginOutputBoundary.class);
        interactor = new LoginInteractor(userDataAccess, recipeDataAccess, loginPresenter);
    }

    @Test
    void execute_SuccessWithPreferences() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        LoginInputData inputData = new LoginInputData(username, password);

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn(password);
        when(mockUser.getName()).thenReturn(username);

        when(userDataAccess.existsByName(username)).thenReturn(true);
        when(userDataAccess.getUser(username)).thenReturn(mockUser);
        List<Integer> ingredients = Arrays.asList(1, 2, 3);
        when(userDataAccess.getIngredientsToAvoid(username)).thenReturn(ingredients);
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class), mock(Recipe.class));
        when(recipeDataAccess.getRandomRecipes(3)).thenReturn(recipes);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDataAccess).existsByName(username);
        verify(userDataAccess, times(2)).getUser(username); // 验证调用了两次
        verify(userDataAccess).setCurrentUser(username);
        verify(userDataAccess).getIngredientsToAvoid(username);
        verify(recipeDataAccess).getRandomRecipes(3);
        verify(loginPresenter).prepareSuccessView(any(LoginOutputData.class));
        verify(loginPresenter, never()).prepareFailView(anyString());
    }

    @Test
    void execute_WrongPassword() {
        // Arrange
        String username = "testUser";
        String password = "wrongPassword";
        LoginInputData inputData = new LoginInputData(username, password);

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn("correctPassword");

        when(userDataAccess.existsByName(username)).thenReturn(true);
        when(userDataAccess.getUser(username)).thenReturn(mockUser);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView(username + ": Wrong password.");
        verify(loginPresenter, never()).prepareSuccessView(any(LoginOutputData.class));
        verify(loginPresenter, never()).preparePreferenceView(any(LoginOutputData.class));
    }

    @Test
    void execute_UserDoesNotExist() {
        // Arrange
        String username = "nonexistentUser";
        String password = "password123";
        LoginInputData inputData = new LoginInputData(username, password);

        when(userDataAccess.existsByName(username)).thenReturn(false);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView(username + ": Account does not exist.");
        verify(loginPresenter, never()).prepareSuccessView(any(LoginOutputData.class));
        verify(loginPresenter, never()).preparePreferenceView(any(LoginOutputData.class));
    }

    @Test
    void execute_SuccessWithoutPreferences() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        LoginInputData inputData = new LoginInputData(username, password);

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn(password);
        when(mockUser.getName()).thenReturn(username);

        when(userDataAccess.existsByName(username)).thenReturn(true);
        when(userDataAccess.getUser(username)).thenReturn(mockUser);
        when(userDataAccess.getIngredientsToAvoid(username)).thenReturn(new ArrayList<>());
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class), mock(Recipe.class));
        when(recipeDataAccess.getRandomRecipes(3)).thenReturn(recipes);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDataAccess).existsByName(username);
        verify(userDataAccess, times(2)).getUser(username); // 验证调用了两次
        verify(userDataAccess).setCurrentUser(username);
        verify(loginPresenter).preparePreferenceView(any(LoginOutputData.class));
        verify(loginPresenter, never()).prepareFailView(anyString());
    }

    @Test
    void execute_UserNull() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        LoginInputData inputData = new LoginInputData(username, password);

        // 先让用户存在，但获取时返回null
        when(userDataAccess.existsByName(username)).thenReturn(true);

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn(password);

        // 第一次调用返回mockUser（用于密码检查），第二次返回null（用于用户获取）
        when(userDataAccess.getUser(username))
                .thenReturn(mockUser)  // 第一次调用返回mock用户，用于密码验证
                .thenReturn(null);     // 第二次调用返回null，触发用户未找到错误

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDataAccess).existsByName(username);
        verify(userDataAccess, times(2)).getUser(username);
        verify(loginPresenter).prepareFailView(username + ": User not found.");
        verify(loginPresenter, never()).prepareSuccessView(any(LoginOutputData.class));
        verify(loginPresenter, never()).preparePreferenceView(any(LoginOutputData.class));
    }

    @Test
    void switchToSignupView_Success() {
        // Act
        interactor.switchToSignupView();

        // Assert
        verify(loginPresenter).switchToSignupView();
    }
}

class LoginInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String password = "password123";

        // Act
        LoginInputData inputData = new LoginInputData(username, password);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(password, inputData.getPassword());
    }

    @Test
    void testConstructorWithEmptyStrings() {
        // Arrange
        String username = "";
        String password = "";

        // Act
        LoginInputData inputData = new LoginInputData(username, password);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(password, inputData.getPassword());
    }
}

class LoginOutputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        List<Integer> ingredients = Arrays.asList(1, 2, 3);
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class));
        boolean useCaseFailed = false;

        // Act
        LoginOutputData outputData = new LoginOutputData(username, ingredients, recipes, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertEquals(ingredients, outputData.getIngredientsToAvoidId());
        assertEquals(recipes, outputData.getRandomRecipes());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithEmptyLists() {
        // Arrange
        String username = "testUser";
        List<Integer> ingredients = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();
        boolean useCaseFailed = true;

        // Act
        LoginOutputData outputData = new LoginOutputData(username, ingredients, recipes, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertTrue(outputData.getIngredientsToAvoidId().isEmpty());
        assertTrue(outputData.getRandomRecipes().isEmpty());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullLists() {
        // Arrange
        String username = null;
        List<Integer> ingredients = null;
        List<Recipe> recipes = null;
        boolean useCaseFailed = false;

        // Act
        LoginOutputData outputData = new LoginOutputData(username, ingredients, recipes, useCaseFailed);

        // Assert
        assertNull(outputData.getUsername());
        assertNull(outputData.getIngredientsToAvoidId());
        assertNull(outputData.getRandomRecipes());
        assertFalse(outputData.isUseCaseFailed());
    }
}