package use_case;

import entities.recipe.CocktailRecipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import entities.recipe.Recipe;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
import use_case.bookmark_recipe.BookmarkRecipeInputData;
import use_case.bookmark_recipe.BookmarkRecipeInteractor;
import use_case.bookmark_recipe.BookmarkRecipeOutputData;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookmarkRecipeInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        int recipeId = 123;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithDifferentValues() {
        // Arrange
        String username = "anotherUser";
        int recipeId = 456;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithEmptyUsername() {
        // Arrange
        String username = "";
        int recipeId = 789;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithZeroRecipeId() {
        // Arrange
        String username = "testUser";
        int recipeId = 0;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }
}

class BookmarkRecipeInteractorTest {

    private BookmarkRecipeDataAccessInterface bookmarkDataAccessMock;
    private SearchRecipeDataAccessInterface searchDataAccessMock;
    private ViewRecipeOutputBoundary viewPresenterMock;
    private BookmarkRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        bookmarkDataAccessMock = mock(BookmarkRecipeDataAccessInterface.class);
        searchDataAccessMock = mock(SearchRecipeDataAccessInterface.class);
        viewPresenterMock = mock(ViewRecipeOutputBoundary.class);
        interactor = new BookmarkRecipeInteractor(bookmarkDataAccessMock, searchDataAccessMock, viewPresenterMock);
    }

    @Test
    void testBookmarkRecipe() {
        // Arrange
        String username = "test_user";
        int recipeId = 101;
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        List<Integer> bookmarkedRecipeIds = Arrays.asList(101, 102);
        List<Recipe> bookmarkedRecipes = Arrays.asList(
                new CocktailRecipe("Recipe1", 101, "Instruction1", null,
                        null, null, "Alcoholic"),
                new CocktailRecipe("Recipe2", 102, "Instruction2", null,
                        null, null, "Non-Alcoholic")
        );

        when(bookmarkDataAccessMock.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccessMock.getBookmarkedRecipes(username)).thenReturn(bookmarkedRecipeIds);
        when(bookmarkDataAccessMock.isBookmarked(username, recipeId)).thenReturn(true);
        when(searchDataAccessMock.getRecipesByIdList(bookmarkedRecipeIds)).thenReturn(bookmarkedRecipes);

        // Act
        interactor.bookmarkRecipe(inputData);

        // Assert
        verify(bookmarkDataAccessMock).bookmarkRecipe(username, recipeId);
        verify(bookmarkDataAccessMock).getBookmarkedRecipes(username);
        verify(bookmarkDataAccessMock).isBookmarked(username, recipeId);
        verify(searchDataAccessMock).getRecipesByIdList(bookmarkedRecipeIds);
        verify(viewPresenterMock).updateBookmarksView(any(BookmarkRecipeOutputData.class));
    }

    @Test
    void testBookmarkRecipeWithNoBookmarks() {
        // Arrange
        String username = "test_user";
        int recipeId = 101;
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        when(bookmarkDataAccessMock.getCurrentUser()).thenReturn(username);
        when(bookmarkDataAccessMock.getBookmarkedRecipes(username)).thenReturn(Collections.emptyList());
        when(bookmarkDataAccessMock.isBookmarked(username, recipeId)).thenReturn(false);
        when(searchDataAccessMock.getRecipesByIdList(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Act
        interactor.bookmarkRecipe(inputData);

        // Assert
        verify(bookmarkDataAccessMock).bookmarkRecipe(username, recipeId);
        verify(bookmarkDataAccessMock).getBookmarkedRecipes(username);
        verify(bookmarkDataAccessMock).isBookmarked(username, recipeId);
        verify(searchDataAccessMock).getRecipesByIdList(Collections.emptyList());
        verify(viewPresenterMock).updateBookmarksView(any(BookmarkRecipeOutputData.class));
    }

    @Test
    void testBookmarkRecipeWithException() {
        // Arrange
        String username = "test_user";
        int recipeId = 101;
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        when(bookmarkDataAccessMock.getCurrentUser()).thenReturn(username);
        doThrow(new RuntimeException("Database error")).when(bookmarkDataAccessMock).bookmarkRecipe(username, recipeId);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> interactor.bookmarkRecipe(inputData));
        assertEquals("Database error", exception.getMessage());

        verify(bookmarkDataAccessMock).bookmarkRecipe(username, recipeId);
        verifyNoInteractions(viewPresenterMock);
    }
}
class BookmarkRecipeOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Recipe recipe1 = new CocktailRecipe("Recipe1", 101, "Instruction1", null, null, null, "Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Recipe2", 102, "Instruction2", null, null, null, "Non-Alcoholic");
        List<Recipe> bookmarkedRecipes = Arrays.asList(recipe1, recipe2);
        boolean isBookmarked = true;

        // Act
        BookmarkRecipeOutputData outputData = new BookmarkRecipeOutputData(bookmarkedRecipes, isBookmarked);

        // Assert
        assertEquals(bookmarkedRecipes, outputData.getBookmarkedRecipes());
        assertEquals(2, outputData.getBookmarkedRecipes().size());
        assertTrue(outputData.isBookmarked());
    }

    @Test
    void testConstructorWithEmptyRecipes() {
        // Arrange
        List<Recipe> bookmarkedRecipes = Arrays.asList();
        boolean isBookmarked = false;

        // Act
        BookmarkRecipeOutputData outputData = new BookmarkRecipeOutputData(bookmarkedRecipes, isBookmarked);

        // Assert
        assertEquals(bookmarkedRecipes, outputData.getBookmarkedRecipes());
        assertEquals(0, outputData.getBookmarkedRecipes().size());
        assertFalse(outputData.isBookmarked());
    }

    @Test
    void testConstructorWithNullRecipes() {
        // Arrange
        List<Recipe> bookmarkedRecipes = null;
        boolean isBookmarked = false;

        // Act
        BookmarkRecipeOutputData outputData = new BookmarkRecipeOutputData(bookmarkedRecipes, isBookmarked);

        // Assert
        assertNull(outputData.getBookmarkedRecipes());
        assertFalse(outputData.isBookmarked());
    }
}
