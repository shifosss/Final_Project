package use_case;

import interface_adapter.search_recipe.SearchRecipePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.recipe.Recipe;
import entities.recipe.CocktailRecipe;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeInputData;
import use_case.search_recipes.SearchRecipeInteractor;
import use_case.search_recipes.SearchRecipeOutputData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchRecipeInteractorTest {
    private SearchRecipeDataAccessInterface recipeDataAccessMock;
    private BookmarkRecipeDataAccessInterface bookmarkDataAccessMock;
    private SearchRecipePresenter recipePresenterMock;
    private SearchRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        recipeDataAccessMock = mock(SearchRecipeDataAccessInterface.class);
        bookmarkDataAccessMock = mock(BookmarkRecipeDataAccessInterface.class);
        recipePresenterMock = mock(SearchRecipePresenter.class);
        interactor = new SearchRecipeInteractor(recipeDataAccessMock, bookmarkDataAccessMock, recipePresenterMock);
    }

    @Test
    void testExecuteSearchWithResults() {
        // Arrange
        String query = "Mojito";
        String username = "test_user";
        List<String> ingredientsToAvoid = Arrays.asList("Salt", "Sugar");
        Recipe recipe = new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic");
        List<Recipe> recipeResults = Arrays.asList(recipe);

        when(bookmarkDataAccessMock.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccessMock.getIngredientsToAvoid(username)).thenReturn(ingredientsToAvoid);
        when(recipeDataAccessMock.searchRecipeByKeyword(query, ingredientsToAvoid)).thenReturn(recipeResults);

        // Act
        interactor.execute(new SearchRecipeInputData(query, Collections.emptyList()));

        // Assert
        verify(recipePresenterMock).prepareSuccessView(argThat((SearchRecipeOutputData outputData) ->
                outputData.getQuery().equals(query) &&
                        outputData.getRecipes().equals(recipeResults) &&
                        !outputData.isUseCaseFailed()
        ));
    }

    @Test
    void testExecuteSearchWithoutResults() {
        // Arrange
        String query = "Nonexistent Recipe";
        String username = "test_user";
        List<String> ingredientsToAvoid = Arrays.asList("Salt", "Sugar");

        when(bookmarkDataAccessMock.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccessMock.getIngredientsToAvoid(username)).thenReturn(ingredientsToAvoid);
        when(recipeDataAccessMock.searchRecipeByKeyword(query, ingredientsToAvoid)).thenReturn(Collections.emptyList());

        // Act
        interactor.execute(new SearchRecipeInputData(query, Collections.emptyList()));

        // Assert
        verify(recipePresenterMock).prepareFailView(argThat((SearchRecipeOutputData outputData) ->
                outputData.getQuery().equals(query) &&
                        outputData.getRecipes().isEmpty() &&
                        outputData.isUseCaseFailed()
        ), eq("Search does not match any recipes."));
    }

    @Test
    void testExecuteSearchByRecipeIds() {
        // Arrange
        String query = "";
        List<Integer> recipeIds = Arrays.asList(101, 102);
        Recipe recipe1 = new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Virgin Mojito", 102, "Mix ingredients", null, null, null, "Non-Alcoholic");
        List<Recipe> recipeResults = Arrays.asList(recipe1, recipe2);

        when(recipeDataAccessMock.getRecipesByIdList(recipeIds)).thenReturn(recipeResults);

        // Act
        interactor.execute(new SearchRecipeInputData(query, recipeIds));

        // Assert
        verify(recipePresenterMock).prepareSuccessView(argThat((SearchRecipeOutputData outputData) ->
                outputData.getQuery().equals(query) &&
                        outputData.getRecipes().equals(recipeResults) &&
                        !outputData.isUseCaseFailed()
        ));
    }

    @Test
    void testSwitchToHomePageView() {
        // Arrange
        SearchRecipeInputData inputData = new SearchRecipeInputData("testQuery", Collections.emptyList());

        // Act
        interactor.switchToHomePageView(inputData);

        // Assert
        verify(recipePresenterMock).switchToHomePageView();
    }

}
class SearchRecipeInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String query = "pasta";
        List<Integer> recipes = Arrays.asList(1,2,3);

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