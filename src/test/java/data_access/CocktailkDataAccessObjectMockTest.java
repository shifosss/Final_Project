package data_access;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import entities.recipe.Recipe;
import entities.recipe.Ingredient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CocktailDataAccessObjectTest {

    private CocktailDataAccessObject cocktailDataAccessObject;
    private static final String API_URL = "http://thecocktaildb.com/api/json/v1/1/";

    @BeforeEach
    public void setUp() {
        cocktailDataAccessObject = Mockito.mock(CocktailDataAccessObject.class);

        // Mock API responses based on request
        Mockito.doAnswer(invocation -> {
            String url = invocation.getArgument(0, String.class);
            if (url.contains("search.php?i=Tequila")) { // Mock for exploreRecipeByIngredients
                JSONObject mockResponse = new JSONObject();
                JSONArray drinks = new JSONArray();
                drinks.put(new JSONObject(
                        "{" +
                                "\"strDrink\": \"Margarita\", " +
                                "\"idDrink\": \"11007\", " +
                                "\"strInstructions\": \"Shake and strain into a cocktail glass\", " +
                                "\"strDrinkThumb\": \"url\", " +
                                "\"strIngredient1\": \"Tequila\" " +
                                "}"
                ));
                mockResponse.put("drinks", drinks);
                return mockResponse;
            } else if (url.contains("list.php?i=list")) { // Mock for getIngredientsList
                JSONObject mockResponse = new JSONObject();
                JSONArray ingredientsList = new JSONArray();
                ingredientsList.put(new JSONObject("{\"strIngredient1\": \"Tequila\"}"));
                ingredientsList.put(new JSONObject("{\"strIngredient1\": \"Vodka\"}"));
                mockResponse.put("drinks", ingredientsList);
                return mockResponse;
            }
            return new JSONObject().put("drinks", new JSONArray()); // Default empty response
        }).when(cocktailDataAccessObject).makeApiRequest(Mockito.anyString());
    }

    // Tests for exploreRecipeByIngredients
    @Test
    public void testExploreRecipeByIngredients_foundRecipes() {
        List<Recipe> recipes = cocktailDataAccessObject.exploreRecipeByIngredients("Tequila");

        assertFalse(recipes.isEmpty(), "Recipes should be found for ingredient");
        assertEquals("Margarita", recipes.get(0).getName());
    }

    @Test
    public void testExploreRecipeByIngredients_noRecipesFound() {
        List<Recipe> recipes = cocktailDataAccessObject.exploreRecipeByIngredients("NonSuchIngredient");

        assertTrue(recipes.isEmpty(), "No recipes should be found for non-existent ingredient");
    }

    @Test
    public void testExploreRecipeByIngredients_invalidResponse() {
        Mockito.doReturn(new JSONObject()).when(cocktailDataAccessObject)
                .makeApiRequest(Mockito.anyString());

        List<Recipe> recipes = cocktailDataAccessObject.exploreRecipeByIngredients("Tequila");

        assertTrue(recipes.isEmpty(), "No recipes should be returned for invalid API response");
    }

    // Tests for getIngredientsList
    @Test
    public void testGetIngredientsList_successfulResponse() {
        List<Ingredient> ingredients = cocktailDataAccessObject.getIngredientsList();

        assertEquals(2, ingredients.size(), "Should return list of 2 ingredients");
        assertEquals("Tequila", ingredients.get(0).getName());
        assertEquals("Vodka", ingredients.get(1).getName());
    }

    @Test
    public void testGetIngredientsList_emptyResponse() {
        Mockito.doReturn(new JSONObject().put("drinks", new JSONArray()))
                .when(cocktailDataAccessObject)
                .makeApiRequest(String.format("%s/list.php?i=list", API_URL));

        List<Ingredient> ingredients = cocktailDataAccessObject.getIngredientsList();

        assertTrue(ingredients.isEmpty(), "Should return empty list for empty response");
    }

    @Test
    public void testGetIngredientsList_invalidResponse() {
        Mockito.doReturn(new JSONObject()).when(cocktailDataAccessObject)
                .makeApiRequest(Mockito.anyString());

        List<Ingredient> ingredients = cocktailDataAccessObject.getIngredientsList();

        assertTrue(ingredients.isEmpty(), "Should return empty list for invalid response");
    }
}
