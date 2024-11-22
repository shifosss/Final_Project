package data_access;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import entities.recipe.Recipe;
import entities.recipe.Ingredient;
import entities.recipe.Recipe;
import entities.recipe.Ingredient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCocktailDataAccessObjectMethods {
    private CocktailDataAccessObject cocktailDataAccessObject;
    private static final String API_URL = "http://thecocktaildb.com/api/json/v1/1/";

    @BeforeEach
    void setUp() {
        cocktailDataAccessObject = spy(new CocktailDataAccessObject());
    }

    @Nested
    class SearchRecipeByKeywordTests {
        @BeforeEach
        void setUp() {
            doAnswer(invocation -> {
                JSONObject mockResponse = new JSONObject();
                JSONArray drinks = new JSONArray();
                drinks.put(new JSONObject()
                        .put("strDrink", "Margarita")
                        .put("idDrink", "11007")
                        .put("strInstructions", "Shake with ice")
                        .put("strDrinkThumb", "http://example.com/margarita.jpg")
                        .put("strIngredient1", "Tequila")
                        .put("strMeasure1", "2 oz"));
                mockResponse.put("drinks", drinks);
                return mockResponse;
            }).when(cocktailDataAccessObject).makeApiRequest(contains("search.php?s="));
        }

        @Test
        void searchRecipeByKeyword_validKeyword_returnsRecipes() {
            List<Recipe> recipes = cocktailDataAccessObject.searchRecipeByKeyword("Margarita");

            assertFalse(recipes.isEmpty(), "Should find recipes for valid keyword");
            assertEquals("Margarita", recipes.get(0).getName());
            verify(cocktailDataAccessObject).makeApiRequest(contains("Margarita"));
        }

        @Test
        void searchRecipeByKeyword_emptyResponse_returnsEmptyList() {
            doReturn(new JSONObject().put("drinks", new JSONArray()))
                    .when(cocktailDataAccessObject)
                    .makeApiRequest(anyString());

            List<Recipe> recipes = cocktailDataAccessObject.searchRecipeByKeyword("NonExistentDrink");

            assertTrue(recipes.isEmpty(), "Should return empty list for no results");
        }
    }

    @Nested
    class SearchRandomRecipeTests {
        @BeforeEach
        void setUp() {
            doAnswer(invocation -> {
                JSONObject mockResponse = new JSONObject();
                JSONArray drinks = new JSONArray();
                drinks.put(new JSONObject()
                        .put("strDrink", "Moscow Mule")
                        .put("idDrink", "11009")
                        .put("strInstructions", "Combine and serve")
                        .put("strDrinkThumb", "http://example.com/moscow-mule.jpg")
                        .put("strIngredient1", "Vodka")
                        .put("strMeasure1", "2 oz"));
                mockResponse.put("drinks", drinks);
                return mockResponse;
            }).when(cocktailDataAccessObject).makeApiRequest(contains("random.php"));
        }

        @Test
        void searchRandomRecipe_returnsValidRecipe() {
            Recipe recipe = cocktailDataAccessObject.searchRandomRecipe();

            assertNotNull(recipe, "Should return a random recipe");
            assertEquals("Moscow Mule", recipe.getName());
            verify(cocktailDataAccessObject).makeApiRequest(contains("random.php"));
        }
    }

    @Nested
    class ExploreRecipeByIngredientsTests {
        @BeforeEach
        void setUp() {
            doAnswer(invocation -> {
                JSONObject mockResponse = new JSONObject();
                JSONArray drinks = new JSONArray();
                drinks.put(new JSONObject()
                        .put("strDrink", "Mojito")
                        .put("idDrink", "11000")
                        .put("strInstructions", "Muddle and mix")
                        .put("strDrinkThumb", "http://example.com/mojito.jpg")
                        .put("strIngredient1", "Rum")
                        .put("strMeasure1", "2 oz"));
                mockResponse.put("drinks", drinks);
                return mockResponse;
            }).when(cocktailDataAccessObject).makeApiRequest(contains("search.php?i="));
        }

        @Test
        void exploreRecipeByIngredients_validIngredient_returnsRecipes() {
            List<Recipe> recipes = cocktailDataAccessObject.exploreRecipeByIngredients("Rum");

            assertFalse(recipes.isEmpty(), "Should find recipes for valid ingredient");
            assertEquals("Mojito", recipes.get(0).getName());
            verify(cocktailDataAccessObject).makeApiRequest(contains("Rum"));
        }

        @Test
        void exploreRecipeByIngredients_invalidIngredient_returnsEmptyList() {
            doReturn(new JSONObject().put("drinks", new JSONArray()))
                    .when(cocktailDataAccessObject)
                    .makeApiRequest(anyString());

            List<Recipe> recipes = cocktailDataAccessObject.exploreRecipeByIngredients("InvalidIngredient");

            assertTrue(recipes.isEmpty(), "Should return empty list for invalid ingredient");
        }
    }

    @Nested
    class GetIngredientsListTests {
        @BeforeEach
        void setUp() {
            // Mock API response for ingredients list
            doAnswer(invocation -> {
                JSONObject mockResponse = new JSONObject();
                JSONArray ingredients = new JSONArray();
                ingredients.put(new JSONObject().put("strIngredient1", "Vodka"));
                ingredients.put(new JSONObject().put("strIngredient1", "Gin"));
                mockResponse.put("drinks", ingredients);
                return mockResponse;
            }).when(cocktailDataAccessObject).makeApiRequest(contains("list.php?i=list"));
        }

        @Test
        void getIngredientsList_returnsValidList() {
            List<Ingredient> ingredients = cocktailDataAccessObject.getIngredientsList();

            assertFalse(ingredients.isEmpty(), "Should return non-empty ingredients list");
            assertEquals(2, ingredients.size(), "Should return correct number of ingredients");
            assertEquals("Vodka", ingredients.get(0).getName());
            assertEquals("Gin", ingredients.get(1).getName());
        }

        @Test
        void getIngredientsList_emptyResponse_returnsEmptyList() {
            doReturn(new JSONObject().put("drinks", new JSONArray()))
                    .when(cocktailDataAccessObject)
                    .makeApiRequest(anyString());

            List<Ingredient> ingredients = cocktailDataAccessObject.getIngredientsList();

            assertTrue(ingredients.isEmpty(), "Should return empty list for empty response");
        }
    }
}
class CocktailDataAccessObjectRealApiTest {

    private CocktailDataAccessObject cocktailDataAccessObject;

    @BeforeEach
    public void setUp() {
        cocktailDataAccessObject = new CocktailDataAccessObject();
    }

    @Test
    public void testSearchRecipeByKeyword_withRealApi() {
        List<Recipe> recipes = cocktailDataAccessObject.searchRecipeByKeyword("Margarita");
        assertFalse(recipes.isEmpty(), "Recipes should be found for keyword");
        assertEquals("Margarita", recipes.get(0).getName());
    }

    @Test
    public void testSearchRandomRecipe_withRealApi() {
        Recipe recipe = cocktailDataAccessObject.searchRandomRecipe();
        assertNotNull(recipe, "Recipe should be returned");
    }


    @Test
    public void testGetIngredientsList_withRealApi() {
        List<Ingredient> ingredientList = cocktailDataAccessObject.getIngredientsList();
        assertFalse(ingredientList.isEmpty(), "Should return list of ingredients");
    }
}
