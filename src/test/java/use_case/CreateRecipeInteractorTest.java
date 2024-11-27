package use_case;


import entities.recipe.CocktailRecipe;
import entities.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import use_case.create_recipe.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomRecipeInteractorTest {

    private CustomRecipeDataAccessInterface customRecipeDataAccessMock;
    private CustomRecipeOutputBoundary customRecipePresenterMock;
    private CustomRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        customRecipeDataAccessMock = mock(CustomRecipeDataAccessInterface.class);
        customRecipePresenterMock = mock(CustomRecipeOutputBoundary.class);
        interactor = new CustomRecipeInteractor(customRecipeDataAccessMock, customRecipePresenterMock);
    }

    @Test
    void testSwitchToHomePageView() {
        // Act
        interactor.switchToHomePageView();

        // Assert
        verify(customRecipePresenterMock).switchToHomePageView();
    }

    @Test
    void testSwitchToCustomRecipeView() {
        // Act
        interactor.switchToCustomRecipeView();

        // Assert
        verify(customRecipePresenterMock).switchToRecipeCreationView();
    }

    @Test
    void testSaveCustomRecipe() {
        // Arrange
        String username = "test_user";
        String recipeName = "Mojito";
        String recipeInstruction = "Mix all ingredients";
        List<String> ingredients = Arrays.asList("Rum", "Mint", "Sugar");
        List<String> measurements = Arrays.asList("60ml", "4 leaves", "2 tsp");
        String isAlcoholic = "Alcoholic";

        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredients, measurements, isAlcoholic);

        List<Recipe> customRecipes = Arrays.asList(
                new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic"),
                new CocktailRecipe("Virgin Mojito", 102, "Mix ingredients", null, null, null, "Non-Alcoholic")
        );

        when(customRecipeDataAccessMock.getCustomRecipes(username)).thenReturn(customRecipes);

        // Act
        interactor.saveCustomRecipe(inputData);

        // Assert
        verify(customRecipeDataAccessMock).createCustomRecipe(
                username,
                recipeName,
                recipeInstruction,
                ingredients,
                measurements,
                isAlcoholic
        );
        verify(customRecipeDataAccessMock, times(2)).getCurrentUser(); // Called twice
        verify(customRecipeDataAccessMock).getCustomRecipes(username);
        verify(customRecipePresenterMock).updateCustomRecipeView(argThat(outputData ->
                outputData.getUsername().equals(username) &&
                        outputData.getCreatedRecipes().equals(customRecipes)
        ));
    }
}
class CustomRecipeInputDataTest {

    @Test
    void testConstructorAndGettersWithValidData() {
        // Arrange
        String recipeName = "Mojito";
        String recipeInstruction = "Mix all ingredients";
        List<String> ingredientNames = Arrays.asList("Rum", "Mint", "Sugar");
        List<String> ingredientMeasurements = Arrays.asList("60ml", "4 leaves", "2 tsp");
        String isAlcoholic = "Alcoholic";

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertEquals(recipeName, inputData.getRecipeName());
        assertEquals(recipeInstruction, inputData.getRecipeInstruction());
        assertEquals(ingredientNames, inputData.getIngredients());
        assertEquals(ingredientMeasurements, inputData.getMeasurements());
        assertEquals(isAlcoholic, inputData.getIsAlcoholic());
    }

    @Test
    void testConstructorWithEmptyLists() {
        // Arrange
        String recipeName = "Empty Recipe";
        String recipeInstruction = "No instructions";
        List<String> ingredientNames = Arrays.asList();
        List<String> ingredientMeasurements = Arrays.asList();
        String isAlcoholic = "Non-Alcoholic";

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertEquals(recipeName, inputData.getRecipeName());
        assertEquals(recipeInstruction, inputData.getRecipeInstruction());
        assertEquals(ingredientNames, inputData.getIngredients());
        assertEquals(ingredientMeasurements, inputData.getMeasurements());
        assertEquals(isAlcoholic, inputData.getIsAlcoholic());
    }

    @Test
    void testConstructorWithNullValues() {
        // Arrange
        String recipeName = null;
        String recipeInstruction = null;
        List<String> ingredientNames = null;
        List<String> ingredientMeasurements = null;
        String isAlcoholic = null;

        // Act
        CustomRecipeInputData inputData = new CustomRecipeInputData(
                recipeName, recipeInstruction, ingredientNames, ingredientMeasurements, isAlcoholic);

        // Assert
        assertNull(inputData.getRecipeName());
        assertNull(inputData.getRecipeInstruction());
        assertNull(inputData.getIngredients());
        assertNull(inputData.getMeasurements());
        assertNull(inputData.getIsAlcoholic());
    }
}
class CustomRecipeOutputDataTest {

    @Test
    void testConstructorAndGettersWithValidRecipes() {
        // Arrange
        Recipe recipe1 = new CocktailRecipe("Mojito", 101, "Mix ingredients", null, null, null, "Alcoholic");
        Recipe recipe2 = new CocktailRecipe("Virgin Mojito", 102, "Mix ingredients", null, null, null, "Non-Alcoholic");
        List<Recipe> customRecipes = Arrays.asList(recipe1, recipe2);

        // Act
        CustomRecipeOutputData outputData = new CustomRecipeOutputData(customRecipes);

        // Assert
        assertEquals(customRecipes, outputData.getCustomRecipes());
        assertEquals(2, outputData.getCustomRecipes().size());
        assertTrue(outputData.getCustomRecipes().contains(recipe1));
        assertTrue(outputData.getCustomRecipes().contains(recipe2));
    }

    @Test
    void testConstructorWithEmptyRecipeList() {
        // Arrange
        List<Recipe> customRecipes = Arrays.asList();

        // Act
        CustomRecipeOutputData outputData = new CustomRecipeOutputData(customRecipes);

        // Assert
        assertEquals(customRecipes, outputData.getCustomRecipes());
        assertTrue(outputData.getCustomRecipes().isEmpty());
    }

    @Test
    void testConstructorWithNullRecipeList() {
        // Arrange
        List<Recipe> customRecipes = null;

        // Act
        CustomRecipeOutputData outputData = new CustomRecipeOutputData(customRecipes);

        // Assert
        assertNull(outputData.getCustomRecipes());
    }
}