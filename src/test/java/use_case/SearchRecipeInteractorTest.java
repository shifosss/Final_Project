package use_case;

import interface_adapter.search_recipe.SearchRecipePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.recipe.Recipe;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeInputData;
import use_case.search_recipes.SearchRecipeInteractor;
import use_case.search_recipes.SearchRecipeOutputData;
import use_case.view_recipe.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchRecipeInteractorTest {
    private SearchRecipeDataAccessInterface recipeDataAccess;
    private BookmarkRecipeDataAccessInterface bookmarkDataAccess;
    private SearchRecipePresenter presenter;
    private SearchRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        recipeDataAccess = mock(SearchRecipeDataAccessInterface.class);
        bookmarkDataAccess = mock(BookmarkRecipeDataAccessInterface.class);
        presenter = mock(SearchRecipePresenter.class);
        interactor = new SearchRecipeInteractor(recipeDataAccess, bookmarkDataAccess, presenter);
    }

    @Test
    void execute_Success() {
        // Arrange
        String query = "pasta";
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class));
        List<String> ingredientsToAvoid = Arrays.asList(mock(String.class), mock(String.class));
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, null);
        when(recipeDataAccess.searchRecipeByKeyword(query, ingredientsToAvoid)).thenReturn(recipes);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(recipeDataAccess).searchRecipeByKeyword(query, ingredientsToAvoid);
        verify(presenter).prepareSuccessView(any(SearchRecipeOutputData.class));
        verify(presenter, never()).prepareFailView(any(SearchRecipeOutputData.class), anyString());
    }

    @Test
    void execute_NoResults() {
        // Arrange
        String query = "nonexistent";
        List<Recipe> emptyList = new ArrayList<>();
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, null);
        List<String> ingredientsToAvoid = Arrays.asList(mock(String.class), mock(String.class));
        when(recipeDataAccess.searchRecipeByKeyword(query, ingredientsToAvoid)).thenReturn(emptyList);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(recipeDataAccess).searchRecipeByKeyword(query, ingredientsToAvoid);
        verify(presenter).prepareFailView(
                any(SearchRecipeOutputData.class),
                eq("Search does not match any recipes.")
        );
        verify(presenter, never()).prepareSuccessView(any(SearchRecipeOutputData.class));
    }

    @Test
    void switchToHomeView_Success() {
        // Arrange
        String query = "pasta";
        List<Integer> recipes = Arrays.asList(mock(Integer.class), mock(Integer.class));
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipes);

        // Act
        interactor.switchToHomePageView(inputData);

        // Assert
        verify(presenter).switchToHomePageView();
    }

    @Test
    void switchToRecipeDetailView_Success() {
        // Arrange
        int recipeId = 123;
        String username = "testUser";
        Recipe recipe = mock(Recipe.class);
        ViewRecipeInputData inputData = new ViewRecipeInputData(recipeId);

        when(bookmarkDataAccess.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccess.isBookmarked(username, recipeId)).thenReturn(true);
        when(recipeDataAccess.getRecipeById(recipeId)).thenReturn(recipe);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(bookmarkDataAccess).getCurrentUser();
        verify(bookmarkDataAccess).isBookmarked(username, recipeId);
        verify(recipeDataAccess).getRecipeById(recipeId);
        verify(presenter).prepareSuccessView(any(ViewRecipeOutputData.class));
        verify(presenter, never()).prepareFailView(any(ViewRecipeOutputData.class), anyString());
    }

    @Test
    void switchToRecipeDetailView_RecipeNotFound() {
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
        verify(presenter).prepareFailView(
                any(ViewRecipeOutputData.class),
                eq("Recipe not found.")
        );
        verify(presenter, never()).prepareSuccessView(any(ViewRecipeOutputData.class));
    }
}

class SearchRecipeInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String query = "pasta";
        List<Integer> recipes = Arrays.asList(mock(Integer.class), mock(Integer.class));

        // Act
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipes);

        // Assert
        assertEquals(query, inputData.getSearchQuery());
        assertEquals(recipes, inputData.getRecipes());
    }

    @Test
    void testConstructorWithEmptyValues() {
        // Arrange
        String query = "";
        List<Integer> recipes = new ArrayList<>();

        // Act
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipes);

        // Assert
        assertEquals(query, inputData.getSearchQuery());
        assertTrue(inputData.getRecipes().isEmpty());
    }

    @Test
    void testConstructorWithNullRecipes() {
        // Arrange
        String query = "pasta";
        List<Integer> recipes = null;

        // Act
        SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipes);

        // Assert
        assertEquals(query, inputData.getSearchQuery());
        assertNull(inputData.getRecipes());
    }
}

class SearchRecipeOutputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String query = "pasta";
        List<Recipe> recipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class));
        boolean useCaseFailed = false;

        // Act
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(query, recipes, useCaseFailed);

        // Assert
        assertEquals(query, outputData.getQuery());
        assertEquals(recipes, outputData.getRecipes());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithEmptyValues() {
        // Arrange
        String query = "";
        List<Recipe> recipes = new ArrayList<>();
        boolean useCaseFailed = true;

        // Act
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(query, recipes, useCaseFailed);

        // Assert
        assertEquals(query, outputData.getQuery());
        assertTrue(outputData.getRecipes().isEmpty());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String query = null;
        List<Recipe> recipes = null;
        boolean useCaseFailed = false;

        // Act
        SearchRecipeOutputData outputData = new SearchRecipeOutputData(query, recipes, useCaseFailed);

        // Assert
        assertNull(outputData.getQuery());
        assertNull(outputData.getRecipes());
        assertFalse(outputData.isUseCaseFailed());
    }
}