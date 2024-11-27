package use_case;

import entities.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.user.User;
import entities.recipe.CocktailRecipe;
import use_case.login.*;
import exceptions.UserNotFound;

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
        List<String> ingredients = Arrays.asList("egg","ginger","lemon");
        when(userDataAccess.getIngredientsToAvoid(username)).thenReturn(ingredients);
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class), mock(Recipe.class));
        when(recipeDataAccess.getRandomRecipes(3)).thenReturn(recipes);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(userDataAccess).existsByName(username);
        verify(userDataAccess, times(2)).getUser(username);
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
        verify(userDataAccess, times(2)).getUser(username);
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

        when(userDataAccess.existsByName(username)).thenReturn(true);

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn(password);

        when(userDataAccess.getUser(username))
                .thenReturn(mockUser)
                .thenReturn(null);

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

    @Test
    void execute_UserNotFoundException() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        LoginInputData inputData = new LoginInputData(username, password);

        when(userDataAccess.existsByName(username)).thenThrow(new UserNotFound("User not found: " + username));

        // Act
        interactor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView("User not found: " + username);
        verify(loginPresenter, never()).prepareSuccessView(any(LoginOutputData.class));
        verify(loginPresenter, never()).preparePreferenceView(any(LoginOutputData.class));
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
    void testConstructorAndGettersWithValidData() {
        // Arrange
        String username = "testUser";
        List<String> ingredientsToAvoidId = Arrays.asList("Salt", "Sugar");
        Recipe recipe1 = new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Virgin Mojito", 102, "Mix ingredients", null, null, null, "Non-Alcoholic");
        List<Recipe> randomRecipes = Arrays.asList(recipe1, recipe2);
        List<Recipe> bookmarkedRecipe = Arrays.asList(recipe1);
        List<String> ingredients = Arrays.asList("Mint", "Lime", "Rum");
        boolean useCaseFailed = false;

        // Act
        LoginOutputData outputData = new LoginOutputData(
                username, ingredientsToAvoidId, randomRecipes, bookmarkedRecipe, ingredients, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertEquals(ingredientsToAvoidId, outputData.getIngredientsToAvoidId());
        assertEquals(randomRecipes, outputData.getRandomRecipes());
        assertEquals(bookmarkedRecipe, outputData.getBookmarkedRecipes());
        assertEquals(ingredients, outputData.getIngredients());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithEmptyLists() {
        // Arrange
        String username = "emptyUser";
        List<String> ingredientsToAvoidId = Arrays.asList();
        List<Recipe> randomRecipes = Arrays.asList();
        List<Recipe> bookmarkedRecipe = Arrays.asList();
        List<String> ingredients = Arrays.asList();
        boolean useCaseFailed = true;

        // Act
        LoginOutputData outputData = new LoginOutputData(
                username, ingredientsToAvoidId, randomRecipes, bookmarkedRecipe, ingredients, useCaseFailed);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertTrue(outputData.getIngredientsToAvoidId().isEmpty());
        assertTrue(outputData.getRandomRecipes().isEmpty());
        assertTrue(outputData.getBookmarkedRecipes().isEmpty());
        assertTrue(outputData.getIngredients().isEmpty());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String username = null;
        List<String> ingredientsToAvoidId = null;
        List<Recipe> randomRecipes = null;
        List<Recipe> bookmarkedRecipe = null;
        List<String> ingredients = null;
        boolean useCaseFailed = true;

        // Act
        LoginOutputData outputData = new LoginOutputData(
                username, ingredientsToAvoidId, randomRecipes, bookmarkedRecipe, ingredients, useCaseFailed);

        // Assert
        assertNull(outputData.getUsername());
        assertNull(outputData.getIngredientsToAvoidId());
        assertNull(outputData.getRandomRecipes());
        assertNull(outputData.getBookmarkedRecipes());
        assertNull(outputData.getIngredients());
        assertTrue(outputData.isUseCaseFailed());
    }
}
