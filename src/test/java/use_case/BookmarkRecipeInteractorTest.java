package use_case;

import org.junit.jupiter.api.Test;
import use_case.bookmark_recipe.BookmarkRecipeInputData;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkRecipeInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        int recipeId = 123;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(username, recipeId);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithDifferentValues() {
        // Arrange
        String username = "anotherUser";
        int recipeId = 456;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(username, recipeId);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithEmptyUsername() {
        // Arrange
        String username = "";
        int recipeId = 789;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(username, recipeId);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithZeroRecipeId() {
        // Arrange
        String username = "testUser";
        int recipeId = 0;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(username, recipeId);

        // Assert
        assertEquals(username, inputData.getUsername());
        assertEquals(recipeId, inputData.getRecipeId());
    }
}