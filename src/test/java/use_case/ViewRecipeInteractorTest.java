package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.recipe.Recipe;
import exceptions.RecipeNotFound;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.view_recipe.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewRecipeInteractorTest {
    private ViewRecipeDataAccessInterface recipeDataAccess;
    private BookmarkRecipeDataAccessInterface bookmarkDataAccess;
    private ViewRecipeOutputBoundary presenter;
    private ViewRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        recipeDataAccess = mock(ViewRecipeDataAccessInterface.class);
        bookmarkDataAccess = mock(BookmarkRecipeDataAccessInterface.class);
        presenter = mock(ViewRecipeOutputBoundary.class);
        interactor = new ViewRecipeInteractor(recipeDataAccess, bookmarkDataAccess, presenter);
    }

    @Test
    void execute_Success() {
        // Arrange
        int recipeId = 123;
        String username = "testUser";
        ViewRecipeInputData inputData = new ViewRecipeInputData(recipeId);
        Recipe mockRecipe = mock(Recipe.class);

        when(bookmarkDataAccess.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccess.isBookmarked(username, recipeId)).thenReturn(true);
        when(recipeDataAccess.getRecipeById(recipeId)).thenReturn(mockRecipe);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(bookmarkDataAccess).getCurrentUser();
        verify(bookmarkDataAccess).isBookmarked(username, recipeId);
        verify(recipeDataAccess).getRecipeById(recipeId);
        verify(presenter).prepareSuccessView(any(ViewRecipeOutputData.class));
        verify(presenter, never()).prepareFailView(any(), anyString());
    }

    @Test
    void execute_RecipeNotFound() {
        // Arrange
        int recipeId = 123;
        String username = "testUser";
        ViewRecipeInputData inputData = new ViewRecipeInputData(recipeId);

        when(bookmarkDataAccess.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccess.isBookmarked(username, recipeId)).thenReturn(false);
        when(recipeDataAccess.getRecipeById(recipeId)).thenReturn(null);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(bookmarkDataAccess).getCurrentUser();
        verify(bookmarkDataAccess).isBookmarked(username, recipeId);
        verify(recipeDataAccess).getRecipeById(recipeId);
        verify(presenter).prepareFailView(any(ViewRecipeOutputData.class), eq("Recipe not found."));
        verify(presenter, never()).prepareSuccessView(any());
    }

    @Test
    void switchToSearchView_Success() {
        // Act
        interactor.switchToSearchView();

        // Assert
        verify(presenter).switchToSearchRecipeView();
    }

    @Test
    void bookmarkRecipe_Success() {
        // Arrange
        int recipeId = 123;
        String username = "testUser";
        ViewRecipeInputData inputData = new ViewRecipeInputData(recipeId);
        Recipe mockRecipe = mock(Recipe.class);

        when(bookmarkDataAccess.getCurrentUser()).thenReturn(username);
        when(recipeDataAccess.getRecipeById(recipeId)).thenReturn(mockRecipe);
        when(bookmarkDataAccess.isBookmarked(username, recipeId)).thenReturn(true);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(bookmarkDataAccess).getCurrentUser();
        verify(bookmarkDataAccess).bookmarkRecipe(username, recipeId);
        verify(recipeDataAccess).getRecipeById(recipeId);
        verify(presenter).prepareSuccessView(any(ViewRecipeOutputData.class));
    }

    @Test
    void bookmarkRecipe_RecipeNotFound() {
        // Arrange
        int recipeId = 123;
        ViewRecipeInputData inputData = new ViewRecipeInputData(recipeId);
        when(recipeDataAccess.getRecipeById(recipeId)).thenReturn(null);

        // Act & Assert
        assertThrows(RecipeNotFound.class, () -> interactor.execute(inputData));
        verify(recipeDataAccess).getRecipeById(recipeId);
        verify(presenter, never()).prepareSuccessView(any());
        verify(bookmarkDataAccess, never()).bookmarkRecipe(anyString(), anyInt());
    }
}

class ViewRecipeInputDataTest {
    @Test
    void testConstructorAndGetter() {
        // Arrange
        int id = 123;

        // Act
        ViewRecipeInputData inputData = new ViewRecipeInputData(id);

        // Assert
        assertEquals(id, inputData.getId());
    }

    @Test
    void testConstructorWithZeroId() {
        // Arrange
        int id = 0;

        // Act
        ViewRecipeInputData inputData = new ViewRecipeInputData(id);

        // Assert
        assertEquals(id, inputData.getId());
    }

    @Test
    void testConstructorWithNegativeId() {
        // Arrange
        int id = -1;

        // Act
        ViewRecipeInputData inputData = new ViewRecipeInputData(id);

        // Assert
        assertEquals(id, inputData.getId());
    }
}

class ViewRecipeOutputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        Recipe mockRecipe = mock(Recipe.class);
        boolean isBookmarked = true;
        boolean useCaseFailed = false;

        // Act
        ViewRecipeOutputData outputData = new ViewRecipeOutputData(mockRecipe, isBookmarked, useCaseFailed);

        // Assert
        assertSame(mockRecipe, outputData.getRecipe());
        assertTrue(outputData.isBookmarked());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullRecipe() {
        // Arrange
        Recipe recipe = null;
        boolean isBookmarked = false;
        boolean useCaseFailed = true;

        // Act
        ViewRecipeOutputData outputData = new ViewRecipeOutputData(recipe, isBookmarked, useCaseFailed);

        // Assert
        assertNull(outputData.getRecipe());
        assertFalse(outputData.isBookmarked());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithAllCombinations() {
        // Test all possible combinations of boolean parameters
        Recipe mockRecipe = mock(Recipe.class);

        // Test case 1: true, true
        ViewRecipeOutputData outputData1 = new ViewRecipeOutputData(mockRecipe, true, true);
        assertTrue(outputData1.isBookmarked());
        assertTrue(outputData1.isUseCaseFailed());

        // Test case 2: true, false
        ViewRecipeOutputData outputData2 = new ViewRecipeOutputData(mockRecipe, true, false);
        assertTrue(outputData2.isBookmarked());
        assertFalse(outputData2.isUseCaseFailed());

        // Test case 3: false, true
        ViewRecipeOutputData outputData3 = new ViewRecipeOutputData(mockRecipe, false, true);
        assertFalse(outputData3.isBookmarked());
        assertTrue(outputData3.isUseCaseFailed());

        // Test case 4: false, false
        ViewRecipeOutputData outputData4 = new ViewRecipeOutputData(mockRecipe, false, false);
        assertFalse(outputData4.isBookmarked());
        assertFalse(outputData4.isUseCaseFailed());
    }
}