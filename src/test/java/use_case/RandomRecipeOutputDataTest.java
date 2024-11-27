package use_case;

import org.junit.jupiter.api.Test;
import entities.recipe.Recipe;
import use_case.random_recipes.RandomRecipeDataAccessInterface;
import use_case.random_recipes.RandomRecipeOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RandomRecipeOutputDataTest {
    @Test
    void testConstructorAndGetter() {
        // Arrange
        List<Recipe> recipes = Arrays.asList(
                mock(Recipe.class),
                mock(Recipe.class),
                mock(Recipe.class)
        );

        // Act
        RandomRecipeOutputData outputData = new RandomRecipeOutputData(recipes);

        // Assert
        assertSame(recipes, outputData.getRecipes());
        assertEquals(3, outputData.getRecipes().size());
    }

    @Test
    void testConstructorWithEmptyList() {
        // Arrange
        List<Recipe> emptyRecipes = new ArrayList<>();

        // Act
        RandomRecipeOutputData outputData = new RandomRecipeOutputData(emptyRecipes);

        // Assert
        assertSame(emptyRecipes, outputData.getRecipes());
        assertTrue(outputData.getRecipes().isEmpty());
    }

    @Test
    void testConstructorWithNullList() {
        // Arrange
        List<Recipe> nullRecipes = null;

        // Act
        RandomRecipeOutputData outputData = new RandomRecipeOutputData(nullRecipes);

        // Assert
        assertNull(outputData.getRecipes());
    }
}

/**
 * Mock implementation of RandomRecipeDataAccessInterface for testing
 */
class MockRandomRecipeDataAccess implements RandomRecipeDataAccessInterface {
    @Test
    void testGetRandomRecipes() {
        // Arrange
        RandomRecipeDataAccessInterface dataAccess = mock(RandomRecipeDataAccessInterface.class);
        int limit = 3;
        List<Recipe> expectedRecipes = Arrays.asList(
                mock(Recipe.class),
                mock(Recipe.class),
                mock(Recipe.class)
        );
        when(dataAccess.getRandomRecipes(limit)).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = dataAccess.getRandomRecipes(limit);

        // Assert
        assertEquals(expectedRecipes, actualRecipes);
        assertEquals(limit, actualRecipes.size());
        verify(dataAccess).getRandomRecipes(limit);
    }

    @Test
    void testGetRandomRecipesWithZeroLimit() {
        // Arrange
        RandomRecipeDataAccessInterface dataAccess = mock(RandomRecipeDataAccessInterface.class);
        int limit = 0;
        List<Recipe> expectedRecipes = new ArrayList<>();
        when(dataAccess.getRandomRecipes(limit)).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = dataAccess.getRandomRecipes(limit);

        // Assert
        assertTrue(actualRecipes.isEmpty());
        verify(dataAccess).getRandomRecipes(limit);
    }

    @Test
    void testGetRandomRecipesWithNegativeLimit() {
        // Arrange
        RandomRecipeDataAccessInterface dataAccess = mock(RandomRecipeDataAccessInterface.class);
        int limit = -1;
        List<Recipe> expectedRecipes = new ArrayList<>();
        when(dataAccess.getRandomRecipes(limit)).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = dataAccess.getRandomRecipes(limit);

        // Assert
        assertTrue(actualRecipes.isEmpty());
        verify(dataAccess).getRandomRecipes(limit);
    }

    @Override
    public List<Recipe> getRandomRecipes(int limit) {
        if (limit <= 0) {
            return new ArrayList<>();
        }
        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            recipes.add(mock(Recipe.class));
        }
        return recipes;
    }
}