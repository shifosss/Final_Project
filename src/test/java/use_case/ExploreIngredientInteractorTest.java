package use_case;

import entities.recipe.Recipe;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.explore_ingredient.*;
import use_case.search_recipes.SearchRecipeOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ExploreIngredientInteractorTest {
    private ExploreIngredientDataAccessInterface dataAccess;
    private ExploreIngredientPresenter presenter;
    private ExploreIngredientInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(ExploreIngredientDataAccessInterface.class);
        presenter = mock(ExploreIngredientPresenter.class);
        interactor = new ExploreIngredientInteractor(dataAccess, presenter);
    }

    @Test
    void switchToRecipes_Success() {
        // Arrange
        String ingredient = "Vodka";
        ExploreIngredientInputData inputData = new ExploreIngredientInputData(ingredient);
        List<Recipe> mockRecipes = Arrays.asList(mock(Recipe.class), mock(Recipe.class));
        when(dataAccess.exploreRecipeByIngredients(ingredient)).thenReturn(mockRecipes);

        // Act
        interactor.switchToRecipes(inputData);

        // Assert
        verify(dataAccess).exploreRecipeByIngredients(ingredient);
        verify(presenter, never()).prepareFailView(anyString());
    }

    @Test
    void switchToRecipes_NoRecipesFound() {
        // Arrange
        String ingredient = "NonExistentIngredient";
        ExploreIngredientInputData inputData = new ExploreIngredientInputData(ingredient);
        when(dataAccess.exploreRecipeByIngredients(ingredient)).thenReturn(new ArrayList<>());

        // Act
        interactor.switchToRecipes(inputData);

        // Assert
        verify(dataAccess).exploreRecipeByIngredients(ingredient);
        verify(presenter).prepareFailView("No recipes found with this ingredient.");
        verify(presenter, never()).prepareSuccessView(any(SearchRecipeOutputData.class));
    }

    @Test
    void switchToExploreIngredients_Success() {
        // Arrange
        List<String> mockIngredients = Arrays.asList("Vodka", "Gin", "Rum");
        when(dataAccess.getIngredientsList()).thenReturn(mockIngredients);

        // Act
        interactor.switchToExploreIngredients();

        // Assert
        verify(dataAccess).getIngredientsList();
        verify(presenter).prepareIngredientsListView(argThat(data ->
                data.getIngredientsList().size() == 3 && !data.isUseCaseFailed()
        ));
    }
}

class ExploreIngredientInputDataTest {
    @Test
    void testConstructorAndGetter() {
        // Arrange
        String ingredientName = "Vodka";

        // Act
        ExploreIngredientInputData inputData = new ExploreIngredientInputData(ingredientName);

        // Assert
        assertEquals(ingredientName, inputData.getIngredientName());
    }

    @Test
    void testConstructorWithEmptyString() {
        // Arrange
        String ingredientName = "";

        // Act
        ExploreIngredientInputData inputData = new ExploreIngredientInputData(ingredientName);

        // Assert
        assertEquals(ingredientName, inputData.getIngredientName());
    }
}

class ExploreIngredientOutputDataTest {
    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<String> ingredientsList = Arrays.asList("Vodka", "Gin", "Rum");
        boolean useCaseFailed = false;

        // Act
        ExploreIngredientOutputData outputData = new ExploreIngredientOutputData(ingredientsList, useCaseFailed);

        // Assert
        assertEquals(ingredientsList, outputData.getIngredientsList());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithEmptyList() {
        // Arrange
        List<String> emptyList = new ArrayList<>();
        boolean useCaseFailed = true;

        // Act
        ExploreIngredientOutputData outputData = new ExploreIngredientOutputData(emptyList, useCaseFailed);

        // Assert
        assertTrue(outputData.getIngredientsList().isEmpty());
        assertTrue(outputData.isUseCaseFailed());
    }

    @Test
    void testConstructorWithNullList() {
        // Arrange
        List<String> nullList = null;
        boolean useCaseFailed = false;

        // Act
        ExploreIngredientOutputData outputData = new ExploreIngredientOutputData(nullList, useCaseFailed);

        // Assert
        assertNull(outputData.getIngredientsList());
        assertFalse(outputData.isUseCaseFailed());
    }
}