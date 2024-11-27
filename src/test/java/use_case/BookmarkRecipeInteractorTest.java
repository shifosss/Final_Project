package use_case;

import org.junit.jupiter.api.Test;
import use_case.bookmark_recipe.BookmarkRecipeInputData;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkRecipeInputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        int recipeId = 123;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithDifferentValues() {
        // Arrange
        int recipeId = 456;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithEmptyUsername() {
        // Arrange
        int recipeId = 789;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }

    @Test
    void testConstructorWithZeroRecipeId() {
        // Arrange
        int recipeId = 0;

        // Act
        BookmarkRecipeInputData inputData = new BookmarkRecipeInputData(recipeId);

        // Assert
        assertEquals(recipeId, inputData.getRecipeId());
    }
}