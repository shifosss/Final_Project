package use_case;

import entities.recipe.CocktailRecipe;
import entities.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import use_case.create_recipe.CustomRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputData;
import use_case.user_profile.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class UserProfileInteractorTest {

    private UserProfileOutputBoundary userProfilePresenterMock;
    private CustomRecipeDataAccessInterface customRecipeDataAccessMock;
    private UserProfileInteractor interactor;

    @BeforeEach
    void setUp() {
        userProfilePresenterMock = mock(UserProfileOutputBoundary.class);
        customRecipeDataAccessMock = mock(CustomRecipeDataAccessInterface.class);
        interactor = new UserProfileInteractor(userProfilePresenterMock, customRecipeDataAccessMock);
    }

    @Test
    void switchToUserView_UserExists() {
        // Arrange
        String username = "testUser";
        Recipe recipe1 = new CocktailRecipe("Recipe1", 1, "Instruction1",
                null, null, null, "Non-Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Recipe2", 2, "Instruction2",
                null, null, null, "Alcoholic");
        List<Recipe> createdRecipes = Arrays.asList(recipe1, recipe2);

        when(customRecipeDataAccessMock.getCurrentUser()).thenReturn(username);
        when(customRecipeDataAccessMock.existsByName(username)).thenReturn(true);
        when(customRecipeDataAccessMock.getCustomRecipes(username)).thenReturn(createdRecipes);

        // Act
        interactor.switchToUserView();

        // Assert
        verify(userProfilePresenterMock).switchToProfileView(argThat(outputData ->
                outputData.getUsername().equals(username) &&
                        outputData.getCreatedRecipes().equals(createdRecipes)
        ));
        verify(userProfilePresenterMock, never()).presentUserNotFound(anyString(), anyString());
    }

    @Test
    void switchToUserView_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        when(customRecipeDataAccessMock.getCurrentUser()).thenReturn(username);
        when(customRecipeDataAccessMock.existsByName(username)).thenReturn(false);

        // Act
        interactor.switchToUserView();

        // Assert
        verify(userProfilePresenterMock).presentUserNotFound("User does not exist", username);
        verify(userProfilePresenterMock, never()).switchToProfileView(any());
    }

    @Test
    void switchToHomePageView_Success() {
        // Act
        interactor.switchToHomePageView();

        // Assert
        verify(userProfilePresenterMock).switchToHomePageView();
    }

    @Test
    void viewRecipeDetail_Success() {
        // Arrange
        int recipeId = 1;
        String username = "testUser";
        Recipe recipe = new CocktailRecipe("Recipe1", recipeId, "Instruction", null, null, null, "Non-Alcoholic");

        when(customRecipeDataAccessMock.getRecipeById(recipeId)).thenReturn(recipe);
        when(customRecipeDataAccessMock.getCurrentUser()).thenReturn(username);
        when(customRecipeDataAccessMock.isBookmarked(username, recipeId)).thenReturn(true);

        // Act
        interactor.viewRecipeDetail(new ViewRecipeInputData(recipeId));

        // Assert
        verify(userProfilePresenterMock).prepareSuccessView(argThat(outputData ->
                outputData.getRecipe().equals(recipe) &&
                        outputData.isBookmarked() &&
                        !outputData.isUseCaseFailed()
        ));
    }
}
class UserProfileInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";

        // Act
        UserProfileInputData inputData = new UserProfileInputData(username);

        // Assert
        assertEquals(username, inputData.getUsername());
    }

    @Test
    void testConstructorWithNullUsername() {
        // Arrange
        String username = null;

        // Act
        UserProfileInputData inputData = new UserProfileInputData(username);

        // Assert
        assertNull(inputData.getUsername());
    }

    @Test
    void testConstructorWithEmptyUsername() {
        // Arrange
        String username = "";

        // Act
        UserProfileInputData inputData = new UserProfileInputData(username);

        // Assert
        assertEquals(username, inputData.getUsername());
    }
}
class UserProfileOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        Recipe recipe1 = new CocktailRecipe("Recipe1", 1, "Instruction1", null, null, null, "Non-Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Recipe2", 2, "Instruction2", null, null, null, "Alcoholic");
        List<Recipe> createdRecipes = Arrays.asList(recipe1, recipe2);

        // Act
        UserProfileOutputData outputData = new UserProfileOutputData(username, createdRecipes);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertEquals(createdRecipes, outputData.getCreatedRecipes());
    }

    @Test
    void testConstructorWithEmptyRecipes() {
        // Arrange
        String username = "testUser";
        List<Recipe> createdRecipes = Collections.emptyList();

        // Act
        UserProfileOutputData outputData = new UserProfileOutputData(username, createdRecipes);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertTrue(outputData.getCreatedRecipes().isEmpty());
    }

    @Test
    void testConstructorWithNullRecipes() {
        // Arrange
        String username = "testUser";
        List<Recipe> createdRecipes = null;

        // Act
        UserProfileOutputData outputData = new UserProfileOutputData(username, createdRecipes);

        // Assert
        assertEquals(username, outputData.getUsername());
        assertNull(outputData.getCreatedRecipes());
    }
}
